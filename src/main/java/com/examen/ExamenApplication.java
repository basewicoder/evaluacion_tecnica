package com.examen;


import com.examen.infraestructure.entity.*;
import com.wicoder.builder.Buffer;
import com.wicoder.builder.CodeBuilder;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Evaluacion Tecnica API", version = "2.0", description = "Evaluacion Tecnica- Information"))
@ComponentScan(basePackages = {"com.examen"})
public class ExamenApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamenApplication.class, args);
	}
	public static void builder(){
		CodeBuilder builder = new CodeBuilder();
		/*builder.setConfig("com.examen.config")
				.setApplication("com.examen.application","/v1/")
				.setInfraestructure("com.examen.infraestructure")
				.setDomain("com.examen.domain")
				.setClassFlex(new Class[]{
					Cliente.class,
					Cuenta.class,
					Transaccion.class
				});*/

	}

}
