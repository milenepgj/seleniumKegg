package com.selenium.seleniumKegg;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilesKeggApplicationTests {

	@Before

	public void setUp() throws Exception {

	}

	@Test

	public void testMergeFiles() {


		try {

			String pathToRead = "/home/milene.guimaraes/Documents/Pessoal/amaranta/DADOS_AMAR_ARTIGO/ANENPI/dados_amaranta/Canto/Parsed";

			Object[] files = Files.list(Paths.get("/home/milene.guimaraes/Documents/Pessoal/amaranta/DADOS_AMAR_ARTIGO/ANENPI/dados_amaranta/Canto/Parsed"))
					.sorted(Comparator.naturalOrder()).toArray();

			String actualEC = "";
			String lines = "";
			for (int i = 0; i < files.length; i++) {
				Path path = ((Path)files[i]);
				String fileName = path.getFileName().toString();
				if (!fileName.contains(".-.pep") && !fileName.contains("merged")){
				    String fileECName = fileName.substring(fileName.indexOf("EC"), fileName.indexOf(".pep"));
					if (actualEC == ""){
						actualEC = fileECName;
					}

					if (!actualEC.equals(fileECName)){
						//Cria o parsed do EC
						createFile(pathToRead + "/" + actualEC + ".merged.txt", lines);
						//Reinicia buffer
						lines = "";
						actualEC = fileECName;
					}else{
                        actualEC = fileECName;
                    }

					Stream<String> streamLines = Files.lines(path);

					String allLines = streamLines
							.map(e -> e.toString())
							.collect(Collectors.joining("\n"));

					lines += allLines;

				}
			}
			/*Files.list(Paths.get(pathToRead))
					.sorted(Comparator.naturalOrder())
                    .filter(Files::isRegularFile)
                    .flatMap(s -> {
                        try {
                            if (!s.getFileName().toString().contains(".-.pep")){

								return Files.lines(s);
							}
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                    .forEach(System.out::println);*/
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(true);

	}

	public static void createFile(String path, String lines) {
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			} else {
				FileOutputStream writer = new FileOutputStream(path);
				writer.write(lines.getBytes());
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
