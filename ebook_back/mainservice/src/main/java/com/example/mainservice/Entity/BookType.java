package com.example.mainservice.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Node
public class BookType {
    @Id
    @GeneratedValue
    private Long id;

    private String type;
    private List<Integer> bookIDs;

    @Relationship(type = "relateTypes")
    public Set<BookType> relateTypes;

    private BookType(){}
    public BookType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public List<Integer> getBookIDs() {
        return bookIDs;
    }
    public void setBookIDs(List<Integer> bookIDs) {
        this.bookIDs = bookIDs;
    }

    @JsonBackReference
    public Set<BookType> getRelateTypes() {
        return relateTypes;
    }

    @JsonBackReference
    public void setRelateTypes(Set<BookType> relateTypes) {
        this.relateTypes = relateTypes;
    }

    public void addRelateType(BookType bookType){
        if(relateTypes == null){
            relateTypes = new HashSet<>();
        }

        relateTypes.add(bookType);
    }

    public void addBookID(int id){
        if(bookIDs == null){
            bookIDs = new ArrayList<>();
        }

        for (Integer bookID : bookIDs) {
            if (bookID == id)
                return;
        }
        bookIDs.add(id);
    }
}
