package publishsubscribe;

public class ThreadsTest extends Thread {
	EmitLog emiter=new EmitLog();
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		String[] argvs={"1","2"};
		try {
			emiter.main(argvs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while (true) {
			ThreadsTest threadA=new ThreadsTest();
			ThreadsTest threadB=new ThreadsTest();
			threadA.start();
			threadB.start();	
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
