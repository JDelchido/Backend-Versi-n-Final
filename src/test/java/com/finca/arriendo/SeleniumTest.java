package com.finca.arriendo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ArriendoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        //Configuración para obtener la versión correcta de ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Asus\\Documents\\chromedriver-win64\\chromedriver.exe");

        //Configuración del WebDriver para cuestiones de compatbilidad
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        //options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--windows-size=1920,1200");

        //Inicializa el WebDriver mediante Chrome y las opciones previas
        driver = new ChromeDriver(options);
    }
    
    @Test
    public void testLoginPage()throws InterruptedException {
        //Navegar hacia la pagina de inicio
        driver.get("http://localhost:8080/h2/login.jsp");

        //Configurar los campos para el inicio de sesión
        WebElement jdbcUrlField = driver.findElement(By.name("url"));
        WebElement usernameField = driver.findElement(By.name("user"));
        WebElement passwordField = driver.findElement(By.name("password"));

        //Introducir los valores para los campos URL, usuario y contraseña respectivamente
        jdbcUrlField.clear();
        jdbcUrlField.sendKeys("jdbc:h2:file:./mydatabase");

        // Espera de 2 segundos antes de introducir el nombre de usuario
        Thread.sleep(2000); // Pausa de 2 segundos

        usernameField.clear();
        usernameField.sendKeys("sa");

        // Esperar 2 segundos para ver lo que sucede
        Thread.sleep(2000); // Pausa de 2 segundos

        passwordField.clear();
        passwordField.sendKeys(""); //ContraseÑa vacía

        // Esperar 2 segundos para ver lo que sucede
        Thread.sleep(2000); // Pausa de 2 segundos

        //Hacer clic en el botón de inicio de sesión
        WebElement connectButton = driver.findElement(By.xpath("//input[@value='Connect']"));
        connectButton.click();

        // Esperar 2 segundos para ver lo que sucede
        Thread.sleep(2000); // Pausa de 2 segundos
    }

    // Cerrar el navegador después de la prueba
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
