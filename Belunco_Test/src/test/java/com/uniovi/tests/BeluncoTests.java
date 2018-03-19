package com.uniovi.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_PublicationView;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.utils.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BeluncoTests {

	static String PathFirefox = "Firefox46.win\\FirefoxPortable.exe";
	static WebDriver driver = getDriver(PathFirefox);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	@Before
	public void setUp() {
		driver.navigate().to(URL);
	}

	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	@BeforeClass
	static public void begin() {
	}

	@AfterClass
	static public void end() {
		driver.quit();
	}

	@Test
	public void ARegVal() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, SeleniumUtils.creaPass(), "belunco", "belunco", "belunco", "belunco");
		PO_View.checkElement(driver, "text", "Ver Usuarios");
	}

	@Test 
	public void BRegInval() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "beluncotest2@uniovi.es", "belunco", "belunco", "belunco1", "belunco");
		PO_View.checkElement(driver, "text", "Las contrasenas no coinciden");
	}

	@Test 
	public void CInVal() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "1", "1");
		PO_View.checkElement(driver, "text", "Ver Usuarios");
	}

	@Test 
	public void DInInVal() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "beluncotes21t@uniovi.es", "belunco");
		// COmprobamos que entramos en los usuarios de la aplicación
		PO_View.checkElement(driver, "text", "El usuario ó la contraseña son incorrectos.");
	}

	@Test 
	public void ELisUsrVal() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario

		PO_LoginView.fillForm(driver, "1", "1");

		// COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "Ver Usuarios");

		// PO_HomeView.clickOption(driver, "user/list", "text", "Ver Usuarios");

		PO_View.checkElement(driver, "text", "Los usuarios que actualmente figuran en el sistema son los siguientes:");

		// Contamos el n�mero de filas de notas
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 5);
		// Ahora nos desconectamos
		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
	}

	@Test 
	public void FLisUsrInVal() {
		driver.navigate().to("http://localhost:8090/user/list");
		PO_View.checkElement(driver, "text", "Identifícate");
	}

	@Test 
	public void GBusUsrVal() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "1", "1");
		PO_View.checkElement(driver, "text", "Ver Usuarios");
		PO_View.checkElement(driver, "text", "Los usuarios que actualmente figuran en el sistema son los siguientes:");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", 10);
		assertTrue(elementos.size() == 5);
		WebElement busqueda = driver.findElement(By.name("searchText"));
		busqueda.click();
		busqueda.clear();
		busqueda.sendKeys("belu");
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "class", "btn-default", 2);
		elementos.get(0).click();
		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
	}

	@Test 
	public void HBusUsrInVal() {
		driver.navigate().to("http://localhost:8090/user/list?searchText=belu");
		PO_View.checkElement(driver, "text", "Identifícate");
	}

	@Test
	public void IInvVal() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "1", "1");
		PO_View.checkElement(driver, "text", "Ver Usuarios");
		PO_View.checkElement(driver, "text", "Los usuarios que actualmente figuran en el sistema son los siguientes:");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", 10);
		WebElement busqueda = driver.findElement(By.name("searchText"));
		busqueda.click();
		busqueda.clear();
		busqueda.sendKeys("belu");
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "class", "btn-default", 2);
		elementos.get(0).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "text", "AÑADIR AMIGO", 2);
		elementos.get(0).click();
		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");

	}

	@Test 
	public void JInvInVal() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "1", "1");
		PO_View.checkElement(driver, "text", "Ver Usuarios");
		PO_View.checkElement(driver, "text", "Los usuarios que actualmente figuran en el sistema son los siguientes:");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", 10);
		WebElement busqueda = driver.findElement(By.name("searchText"));
		busqueda.click();
		busqueda.clear();
		busqueda.sendKeys("belu");
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "class", "btn-default", 2);
		elementos.get(0).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "text", "PETICIÓN ENVIADA", 2);
		assertFalse(elementos.get(0).isEnabled());
		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
	}

	@Test 
	public void KLisInvVal() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, SeleniumUtils.creaPass(), "belunco", "belunco", "belunco", "belunco");
		PO_View.checkElement(driver, "text", "Ver Usuarios");
		PO_HomeView.clickOption(driver, "user/list", "text", "Ver Usuarios");
		List<WebElement> elementos2 = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		elementos2 = SeleniumUtils.EsperaCargaPagina(driver, "text", "AÑADIR AMIGO", 2);
		elementos2.get(0).click();
		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");

		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "1", "1");
		PO_View.checkElement(driver, "text", "Ver Usuarios");
		PO_HomeView.clickOption(driver, "user/list", "text", "Ver Usuarios");
		PO_View.checkElement(driver, "text", "Los usuarios que actualmente figuran en el sistema son los siguientes:");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 5);
		PO_HomeView.clickOption(driver, "peticion/list", "text", "Ver peticiones");
		PO_View.checkElement(driver, "text", "Los usuarios que actualmente figuran en el sistema son los siguientes:");
		List<WebElement> elementos3 = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos3.size() >= 1);
		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
	}

	@Test 
	public void LAcepInvVal() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "1", "1");
		PO_View.checkElement(driver, "text", "Ver Usuarios");
		PO_HomeView.clickOption(driver, "user/list", "text", "Ver Usuarios");
		PO_View.checkElement(driver, "text", "Los usuarios que actualmente figuran en el sistema son los siguientes:");
		PO_HomeView.clickOption(driver, "peticion/list", "text", "Ver peticiones");
		PO_View.checkElement(driver, "text", "Los usuarios que actualmente figuran en el sistema son los siguientes:");
		List<WebElement> elementos2 = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		elementos2 = SeleniumUtils.EsperaCargaPagina(driver, "text", "ACEPTAR SOLICITUD", 2);
		elementos2.get(0).click();
		assertFalse(elementos2.get(0).isEnabled());
		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
	}

	@Test 
	public void MListAmiVal() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "1", "1");
		PO_HomeView.clickOption(driver, "friend/list", "text", "Ver amigos");
		PO_View.checkElement(driver, "text",
				"Los usuarios que actualmente figuran como tus amigos son los siguientes:");
		List<WebElement> elementos2 = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos2.size() >= 1);
		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
	}

	@Test
	public void NPubVal() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "1", "1");
		PO_View.checkElement(driver, "text", "Ver Usuarios");
		//Accedemos a publicaciones
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'publication-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'/publication/add')]");
		elementos.get(0).click();
		//Rellenamos el formulario
		SeleniumUtils.EsperaCargaPagina(driver,"class", "control-label col-sm-2" , 5);
		PO_PublicationView.fillForm(driver, "Moda españa", "Dulceida como pionera.");
		//Desconectamos
		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
	}
	
	@Test
	public void RAdInVal() {
		driver.navigate().to("http://localhost:8090/admin/login");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "5", "1");
		PO_View.checkElement(driver, "text", "Usuarios");
		//Accedemos a los usuarios y los contamos
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 5);
		//Desconectamos
		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
	}
	
	@Test
	public void SAdInInVal() {
		driver.navigate().to("http://localhost:8090/admin/login");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "1", "1");
		PO_View.checkElement(driver, "text", "Access is denied");
	}
	
	@Test
	public void TAdLisUsrVal() {
		driver.navigate().to("http://localhost:8090/admin/login");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "5", "1");
		PO_View.checkElement(driver, "text", "Usuarios");
		//Accedemos a los usuarios y los contamos
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 5);
		//Desconectamos
		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
	}
	
	@Test
	public void UAdBorUsrVal() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, SeleniumUtils.creaPass(), "xxxxxx", "xxxxxx", "xxxxxx", "xxxxxx");
		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
		driver.navigate().to("http://localhost:8090/admin/login");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "5", "1");
		PO_View.checkElement(driver, "text", "Usuarios");
		//Accedemos a los usuarios y los contamos
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 5);
		//Borramos un usuario
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "text", "ELIMINAR USUARIO", 2);
		elementos.get(elementos.size()-1).click();
		assertFalse(elementos.get(elementos.size()-1).isEnabled());
		//Desconectamos
		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
	}
	
	@Test
	public void VAdBorUsrInVal() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "1", "1");
		//Intentamos borrar pero el acceso es denegado
		driver.navigate().to("http://localhost:8090/admin/2/delete");
		PO_View.checkElement(driver, "text", "Access is denied");
	}
	
	
}
