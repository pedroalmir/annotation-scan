package com.pedroalmir.annotationscan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import com.pedroalmir.annotationscan.annotation.CheckPermission;
import com.pedroalmir.annotationscan.annotation.CustomAnnotationFilter;

/**
 * Annotation Scan Inspired by
 * https://www.baeldung.com/java-scan-annotations-runtime
 */
public class App {
	public static void main(String[] args) {
		ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
		provider.addIncludeFilter(new CustomAnnotationFilter(CheckPermission.class));

		Set<BeanDefinition> beanDefs = provider.findCandidateComponents("com.pedroalmir.annotationscan");
		List<String> resources = new ArrayList<String>();
		
		/*
		 * O intuito desse trecho de código é identificar automaticamente
		 * os métodos anotados com a anotação CheckPermission. Nesse caso, 
		 * cada método desse representa um recurso que o usuário pode acessar.
		 * Usamos essa informação para exibir no frontend uma tela de configuração.
		 * Para cada role, o admin deve indicar quais recursos pode ser acessados.
		 * No spring, podemos usar filters. Ver link:
		 * 
		 * https://medium.com/@elouadinouhaila566/component-scanning-in-spring-boot-how-it-works-and-customizing-with-componentscan-d3962a62d926
		 * */
		for (BeanDefinition bd : beanDefs) {
			if (bd instanceof AnnotatedBeanDefinition) {
				Set<MethodMetadata> annotatedMethods = ((AnnotatedBeanDefinition) bd).getMetadata().getAnnotatedMethods(CheckPermission.class.getCanonicalName());
				for (MethodMetadata metadata : annotatedMethods) {
					resources.add(metadata.getDeclaringClassName() + " - " + metadata.getMethodName());
				}
			}
		}
		
		/*
		 * No banco, precisaremos de uma estrutura para armazenar
		 * a relação entre as roles e os métodos acessíveis. Aqui,
		 * por exemplo, estou usando uma HashMap
		 * */
		HashMap<String, String[]> mapaPermissoes = new HashMap<String, String[]>() {{
            put("AdminRole", new String[] {"com.pedroalmir.annotationscan.controller.SampleController - doSomeAction", "com.pedroalmir.annotationscan.controller.SampleController - doSomeAction2"});
            put("UserRole", new String[] {"com.pedroalmir.annotationscan.controller.SampleController - doSomeAction3"});
        }};
        
        /*
         * Com a estrutura criada, agora, nos filters de execução do Spring,
         * antes da execução do método anotado com @CheckPermission, acessamos o mapa de permissões
         * e verificamos se o usuário que está tentando acessar aquele método possui a role requerida.
         * */
	}
}
