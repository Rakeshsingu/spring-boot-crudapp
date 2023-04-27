package Specifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.luv2code.springboot.crudapp.entity.Student;

import jakarta.persistence.criteria.Predicate;

@Component
public class StudentSpecification {
	public static Specification<Student> getStudentByCriteria(String firstName, String lastName, String email,String consistOf,String endsWith) {
	    return ((root, criteriaQuery, criteriaBuilder) -> {
	        List<Predicate> predicates = new ArrayList<>();
	        
	        if(consistOf != null) {
	        	predicates.add(criteriaBuilder.like(root.get("firstName"), "%" +firstName + "%"));
	        }
	        if(endsWith != null) {
	        	predicates.add(criteriaBuilder.like(root.get("firstName"), "%"+ endsWith ));
	        }

	        if (firstName != null) {
	            predicates.add(criteriaBuilder.equal(root.get("firstName"), firstName));
	        }

	        if (lastName != null) {
	            predicates.add(criteriaBuilder.equal(root.get("lastName"), lastName));
	        }

	        if (email != null) {
	            predicates.add(criteriaBuilder.equal(root.get("email"), email));
	        }
	        if (predicates.size() == 0) {
	            return null;
	        } else if (predicates.size() == 1) {
	            return predicates.get(0);
	        } else {
	            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	        }
	    });
	}
	
	
	public static Specification<Student> containsEmail(String email){
		return((root,criteriaQuery,criteriaBuilder) -> {
			return criteriaBuilder.like(root.get("email"),email);
		});
	}

	public static Specification<Student> containsLastName(String lastname){
		return((root,criteriaQuery,criteriaBuilder) -> {
			return criteriaBuilder.equal(root.get("lastName"),lastname);
		});
	}
}
