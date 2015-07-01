package com.brightfuture.application.persistence;

import com.orm.SugarRecord;

/**
 * Created by BJohnson on 29/06/2015.
 */
public class Person extends SugarRecord<Person> {
    private String firstName;
    private String lastName;
    private int attended;
    private int authorisedAbsences;
    private int unauthorisedAnsences;

    /** Empty public constructor required by Sugar ORM */
    public Person (){}

    public Person(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(String firstName, String lastName, int attended, int authorisedAbsences, int unauthorisedAbsences) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.attended = attended;
        this.authorisedAbsences = authorisedAbsences;
        this.unauthorisedAnsences = unauthorisedAbsences;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public int getAttended(){
        return attended;
    }
    public int getAuthorisedAbsences(){
        return authorisedAbsences;
    }
    public int getUnauthorisedAnsences(){
        return unauthorisedAnsences;
    }
}
