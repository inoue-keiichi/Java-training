package ch03.ex02;

public class TrackMaskChange {
	private static int xMask;
	private static int yMask;
	private static int fullMask;
	
	TrackMaskChange(){
		Y y = new Y();	
	}
	
	public static void main(String[] args) {
		System.out.printf("xMask    ");
		System.out.printf("yMask    ");
		System.out.printf("fullMask%n");
		System.out.printf("---------------------------------%n");
		System.out.printf("%04x     ", xMask);
		System.out.printf("%04x     ", yMask);
		System.out.printf("%04x%n", fullMask);
		TrackMaskChange track = new TrackMaskChange();	
	}

	 class X {
		protected int xMask = 0x00ff;
		protected int fullMask;

		public X() {
			TrackMaskChange.xMask = xMask;
			TrackMaskChange.fullMask = fullMask;
			System.out.printf("%04x     ", TrackMaskChange.xMask);
			System.out.printf("%04x     ", TrackMaskChange.yMask);
			System.out.printf("%04x%n", TrackMaskChange.fullMask);
			fullMask = xMask;
			TrackMaskChange.xMask = xMask;
			TrackMaskChange.fullMask = fullMask;
			System.out.printf("%04x     ", TrackMaskChange.xMask);
			System.out.printf("%04x     ", TrackMaskChange.yMask);
			System.out.printf("%04x%n", TrackMaskChange.fullMask);
		}

		public int mask(int orig) {
			return (orig & fullMask);
		}
	}

	class Y extends X {
		protected int yMask = 0xff00;
		
		public Y() {
			TrackMaskChange.xMask = xMask;
			TrackMaskChange.yMask = yMask;
			TrackMaskChange.fullMask = fullMask;
			System.out.printf("%04x     ", TrackMaskChange.xMask);
			System.out.printf("%04x     ", TrackMaskChange.yMask);
			System.out.printf("%04x%n", TrackMaskChange.fullMask);
			fullMask |= yMask;
			TrackMaskChange.xMask = xMask;
			TrackMaskChange.yMask = yMask;
			TrackMaskChange.fullMask = fullMask;
			System.out.printf("%04x     ", TrackMaskChange.xMask);
			System.out.printf("%04x     ", TrackMaskChange.yMask);
			System.out.printf("%04x%n", TrackMaskChange.fullMask);
		}
	}
}
