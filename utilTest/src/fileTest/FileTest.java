package fileTest;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class FileTest {

	@Test
	public void test1() {
		
		File file = new File("a");
		
		System.out.println(file.getName());
		System.out.println(file.getParent());
		System.out.println(file.getAbsoluteFile());
		System.out.println(file.getAbsoluteFile().getParent());
	}
	
	@Test
	public void test2() throws IOException {
		
		File file = new File("" + System.currentTimeMillis());
		
		System.out.println(file.exists());
		System.out.println(file.mkdir());
		System.out.println(file.createNewFile());
		System.out.println(file.exists());
		
	}
	
	@Test
	public void test3() throws IOException {
		
		File file = new File("src/fileTest/"+ System.currentTimeMillis());
		
		System.out.println(file.exists());
		System.out.println(file.createNewFile());
		System.out.println(file.exists());
		
	}

}
