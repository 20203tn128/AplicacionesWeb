package mx.edu.utez.marketplace.category.controller;

import mx.edu.utez.marketplace.category.model.Category;
import mx.edu.utez.marketplace.category.model.CategoryRepository;
import mx.edu.utez.marketplace.utils.Message;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {
    @Autowired
    //inyeccion de dependencia
    CategoryRepository categoryRepository;

    @Transactional
    public ResponseEntity<Message> findAll(){
        return new ResponseEntity<>(new Message("ok",false,
                categoryRepository.findAll()), HttpStatus.OK);
    }
    @Transactional
    public ResponseEntity<Message> findById(Long id){
        if (categoryRepository.existsById(id)) {
            return new ResponseEntity<>(new Message("ok", false, categoryRepository.findById(id)), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("No se encontro la categoria",true,null),HttpStatus.BAD_REQUEST);
    }
    @Transactional(rollbackOn = {Exception.class})
    public ResponseEntity<Message>save(Category category){
        Optional<Category> existsCategory=categoryRepository.findByDescription(category.getDescription());
        if (existsCategory.isPresent()){
            return new ResponseEntity<>(new Message("La categoria ya existe",true,null),HttpStatus.BAD_REQUEST);
        }
        Category savedCategory = categoryRepository.saveAndFlush(category);
        return new ResponseEntity<>(new Message("Categoria registrada",false,savedCategory),HttpStatus.OK);
    }

    @Transactional(rollbackOn = {Exception.class})
    public ResponseEntity<Message> update(Category category){
        if (categoryRepository.existsById(category.getId())){
         return new ResponseEntity<>(new Message("Categoria actualizada",false,categoryRepository.saveAndFlush(category)),HttpStatus.OK);
        }
        return  new ResponseEntity<>(new Message("La  categoria no existe",true,null),HttpStatus.BAD_REQUEST);

    }
}
