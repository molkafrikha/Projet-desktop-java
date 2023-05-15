/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.entities;

/**
 *
 * @author SAHBI
 */
public class Questions {
    private int Question_id;
    private int Sondage_id;
    private Sondage Sondage; 
    private String question;
    private String type;
   
    public Questions() {
    }

    public Questions(int Question_id,Sondage Sondage, String question, String type) {
        this.Question_id = Question_id;
        this.Sondage= Sondage;
        this.question = question;
        this.type = type;
        
    }

    public Questions(String question,Sondage Sondage, String type) {
        
        this.Sondage= Sondage;
        this.question = question;
        this.type = type;
       
    }

    public Questions(Sondage Sondage) {
        this.Sondage = Sondage;
    }

    public void setSondage_id(int Sondage_id) {
        this.Sondage_id = Sondage_id;
    }

    public int getSondage_id() {
        return Sondage_id;
    }

    public int getQuestion_id() {
        return Question_id;
    }

        public String getQuestion() {
        return question;
    }

    public String getType() {
        return type;
    }

    public void setQuestion_id(int Question_id) {
        this.Question_id = Question_id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Sondage getSondage() {
        return Sondage;
    }

    public void setSondage(Sondage Sondage) {
        this.Sondage = Sondage;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Questions{" + "Question_id=" + Question_id + ", Sondage_id=" + Sondage_id + ", Sondage=" + Sondage + ", question=" + question + ", type=" + type +  '}';
    }
   
}

    
