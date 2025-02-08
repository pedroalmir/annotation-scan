/**
 * 
 */
package com.pedroalmir.annotationscan.annotation;

import java.lang.annotation.Annotation;

import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * @author Pedro Almir
 *
 */
public class CustomAnnotationFilter extends AnnotationTypeFilter {

	/**
	 * @param annotationType
	 */
	public CustomAnnotationFilter(Class<? extends Annotation> annotationType) {
		super(annotationType);
	}
	
	@Override
	protected boolean matchSelf(MetadataReader metadataReader) {
		AnnotationMetadata metadata = metadataReader.getAnnotationMetadata();
        return metadata.hasAnnotatedMethods(getAnnotationType().getName());
	}
}
