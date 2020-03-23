package main;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.clazz.FieldInfo;
import main.clazz.MemberInfo;
import main.clazz.MethodInfo;

public class DialogService {
	private final String[] FIELD_HEADER = { "prefix", "name", "value" };
	private final String[] METHOD_HEADER = { "prefix", "name" };
	private final String[] ELEMENT_HEADER = { "index", "value" };

	private Object instance;
	private String instanceName;

	public Object getInstance() {
		return instance;
	}

	public void setInstance(final Object instance) {
		this.instance = instance;
	}

	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(final String instanceName) {
		this.instanceName = instanceName;
	}

	public String getClassName(final Object instance) {
		final StringBuilder clazzName = new StringBuilder();
		final Class<?> clazz = instance.getClass();

		AnnotatedType superClazz = clazz.getAnnotatedSuperclass();
		AnnotatedType[] interfaceClazzes = clazz.getAnnotatedInterfaces();
		clazzName.append(stringConverter(clazz.getModifiers()));
		clazzName.append("class ");
		clazzName.append(clazz.getSimpleName());
		if (Objects.nonNull(superClazz) && !superClazz.getType().getTypeName().equals("java.lang.Object")) {
			clazzName.append(" extends ");
			clazzName.append(superClazz.getType().getTypeName());
		}
		if (interfaceClazzes.length > 0) {
			clazzName.append(" implements ");
			for (int i = 0; i < interfaceClazzes.length; i++) {
				clazzName.append(interfaceClazzes[i].getType().getTypeName());
				if (i != interfaceClazzes.length - 1) {
					clazzName.append(" ,");
				}
			}
		}
		return clazzName.toString();
	}

	public JTable[] createMemberTables(final Object instance)
			throws IllegalArgumentException, IllegalAccessException {

		final List<FieldInfo> fieldInfoList = createFieldInfos(instance);
		final List<MethodInfo> methodInfoList = createMethodInfos(instance);
		final FieldInfo[] fieldInfos = fieldInfoList.toArray(new FieldInfo[fieldInfoList.size()]);
		final MethodInfo[] methodInfos = methodInfoList.toArray(new MethodInfo[methodInfoList.size()]);

		final JTable[] memberTables = new JTable[2];
		memberTables[0] = createMemberTable(FIELD_HEADER, fieldInfos);
		memberTables[1] = createMemberTable(METHOD_HEADER, methodInfos);
		return memberTables;
	}

	private JTable createMemberTable(final String[] header, final MemberInfo[] memberInfos) {
		// JTableを作成するための行列を作成
		//final TypeVariable<?>[] typeParam = memberInfos.getClass().getTypeParameters();
		String[][] memberMatrix = null;
		if (memberInfos instanceof FieldInfo[]) {
			memberMatrix = createFieldMatrix(header, (FieldInfo[]) memberInfos);
		} else if (memberInfos instanceof MethodInfo[]) {
			memberMatrix = createMethodMatrix(header, (MethodInfo[]) memberInfos);
		} else {
			//TODO: 他のクラスは例外処理
		}
		// JTableに行列データを追加する
		final DefaultTableModel model = new DefaultTableModel(header, 0);
		for (String[] row : memberMatrix) {
			model.addRow(row);
		}

		return new JTable(model);
	}

	private List<FieldInfo> createFieldInfos(final Object instance)
			throws IllegalArgumentException, IllegalAccessException {
		final Class<?> clazz = instance.getClass();
		final List<FieldInfo> fieldInfos = new ArrayList<>();
		boolean accessible;
		int modifier;
		Type type;

		// フィールドは修飾子、型つける。public以外も表示する
		for (Field field : clazz.getDeclaredFields()) {
			accessible = field.isAccessible();
			try {
				field.setAccessible(true);
				modifier = field.getModifiers();
				type = field.getGenericType();
				FieldInfo fieldInfo = new FieldInfo();
				StringBuilder prefix = new StringBuilder();
				prefix.append(stringConverter(modifier));
				prefix.append(stringConverter(type));
				fieldInfo.setPrefix(prefix.toString());
				fieldInfo.setName(field.getName());
				fieldInfo.setValue(field.get(instance));
				fieldInfos.add(fieldInfo);
			} finally {
				field.setAccessible(accessible);
			}
		}
		return fieldInfos;
	}

	private List<MethodInfo> createMethodInfos(final Object instance)
			throws IllegalArgumentException, IllegalAccessException {
		final Class<?> clazz = instance.getClass();
		final List<MethodInfo> methodInfos = new ArrayList<>();
		boolean accessible;
		int modifier;
		Type type;

		// メソッドは修飾子、返り値の型つける。public以外も表示する
		for (Method method : clazz.getDeclaredMethods()) {
			accessible = method.isAccessible();
			try {
				method.setAccessible(true);
				modifier = method.getModifiers();
				type = method.getGenericReturnType();
				MethodInfo methodInfo = new MethodInfo();
				StringBuilder prefix = new StringBuilder();
				prefix.append(stringConverter(modifier));
				prefix.append(stringConverter(type));

				methodInfo.setPrefix(prefix.toString());
				methodInfo.setName(method.getName());
				methodInfo.setArguments(method.getGenericParameterTypes());
				methodInfo.setExceptions(method.getGenericExceptionTypes());
				methodInfos.add(methodInfo);
			} finally {
				method.setAccessible(accessible);
			}
		}
		return methodInfos;
	}

	private String[][] createFieldMatrix(final String[] header, final FieldInfo[] fieldInfos) {
		final String[][] data = new String[fieldInfos.length][header.length];
		int i = 0;
		for (final FieldInfo fieldInfo : fieldInfos) {
			data[i][0] = fieldInfo.getPrefix();
			data[i][1] = fieldInfo.getName();
			//data[i][2] = new StringBuilder().append(fieldInfo.getValue()).toString();
			data[i][2] = stringConverter(fieldInfo.getValue());
			i++;
		}
		return data;
	}

	private String[][] createMethodMatrix(final String[] header, final MethodInfo[] methodInfos) {
		final String[][] data = new String[methodInfos.length][header.length];
		int i = 0;
		for (final MethodInfo methodInfo : methodInfos) {
			data[i][0] = methodInfo.getPrefix();
			data[i][1] = getMethodName(methodInfo);
			i++;
		}
		return data;
	}

	private String getMethodName(final MethodInfo methodInfo) {
		StringBuilder name = new StringBuilder();
		name.append(methodInfo.getName());

		Type[] params = methodInfo.getArguments();
		if (params.length < 1) {
			name.append("() ");
		} else {
			name.append("( ");
			for (int i = 0; i < params.length; i++) {
				name.append(stringConverter(params[i]));
				if (i < params.length - 1) {
					name.append(" ,");
				}
			}
			name.append(") ");
		}
		if (methodInfo.getExceptions().length > 1) {
			name.append("throws ");
			Type[] errorClazzes = methodInfo.getExceptions();
			for (int i = 0; i < errorClazzes.length; i++) {
				name.append(errorClazzes[i].getTypeName());
				if (i < errorClazzes.length - 1) {
					name.append(" ,");
				}
			}
		}
		return name.toString();
	}

	private String stringConverter(Type type) {

		if (!type.getClass().isArray()) {
			return type.getTypeName() + " ";
		}
		return stringConverter(type.getClass().getComponentType()) + "[] ";
	}

	private String stringConverter(int modifier) {
		StringBuilder sb = new StringBuilder();
		if (Modifier.isPrivate(modifier))
			sb.append("private ");
		if (Modifier.isPublic(modifier))
			sb.append("public ");
		if (Modifier.isProtected(modifier))
			sb.append("protected ");
		if (Modifier.isStatic(modifier))
			sb.append("static ");
		if (Modifier.isAbstract(modifier))
			sb.append("abstract ");
		if (Modifier.isFinal(modifier))
			sb.append("final ");
		if (Modifier.isInterface(modifier))
			sb.append("interface ");
		if (Modifier.isNative(modifier))
			sb.append("native ");
		if (Modifier.isStrict(modifier))
			sb.append("strict ");
		if (Modifier.isSynchronized(modifier))
			sb.append("synchoronized ");
		if (Modifier.isTransient(modifier))
			sb.append("transient ");
		if (Modifier.isVolatile(modifier))
			sb.append("volatile ");
		return sb.toString();
	}

	private String stringConverter(Object value) {
		if (!value.getClass().isArray()) {
			return value.toString();
		}
		return convertArrayValue(castArrayObject(value));
	}

	private Object[] castArrayObject(final Object array) {
		final Object[] castedArray = new Object[Array.getLength(array)];
		for (int i = 0; i < castedArray.length; i++) {
			castedArray[i] = Array.get(array, i);
		}
		return castedArray;
	}

	private String convertArrayValue(Object[] array) {
		StringBuilder arraySb = new StringBuilder();

		arraySb.append("[ ");
		for (Object value : array) {
			if (!value.getClass().isArray()) {
				arraySb.append(value);
			} else {
				arraySb.append(convertArrayValue(castArrayObject(value)));
			}
			arraySb.append(" ,");
		}
		arraySb.deleteCharAt(arraySb.lastIndexOf(" ,") + 1);
		arraySb.append("] ");
		return arraySb.toString();
	}

	public JTable createElementTable(final Object instance) {
		Object[] elements = (Object[]) instance;
		String[][] elementMatrix = new String[elements.length][2];
		for (int i = 0; i < elements.length; i++) {
			elementMatrix[i][0] = Integer.toString(i);
			if (Objects.isNull(elements[i])) {
				elementMatrix[i][1] = null;
			} else {
				elementMatrix[i][1] = stringConverter(elements[i].toString());
			}
		}

		// JTableに行列データを追加する
		final DefaultTableModel model = new DefaultTableModel(ELEMENT_HEADER, 0);
		for (String[] row : elementMatrix) {
			model.addRow(row);
		}
		return new JTable(model);
	}
}
