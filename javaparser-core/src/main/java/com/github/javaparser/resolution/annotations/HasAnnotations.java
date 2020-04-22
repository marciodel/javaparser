package com.github.javaparser.resolution.annotations;

import java.util.List;

/**
 * @author Marcio "DEL" Alves
 */
public interface HasAnnotations {
    default List<ResolvedAnnotationExpression> getAnnotations(){
       throw new UnsupportedOperationException();
    }   
}