package com.androiddesdecero.rxudemyfinal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Empleado {

    private int id;
    private String nombre;
    private String puesto;
    private Date antiguedad;
    private Double salario;
    private Double plusSalario;

    public Empleado(int id, String nombre, String puesto, Date antiguedad, Double salario, Double plusSalario) {
        this.id = id;
        this.nombre = nombre;
        this.puesto = puesto;
        this.antiguedad = antiguedad;
        this.salario = salario;
        this.plusSalario = plusSalario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Date getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Date antiguedad) {
        this.antiguedad = antiguedad;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Double getPlusSalario() {
        return plusSalario;
    }

    public void setPlusSalario(Double plusSalario) {
        this.plusSalario = plusSalario;
    }

    public static final List<Empleado> setUpEmpleados(){
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado(1,"Alberto", "Developer", new Date(), 2000.00, 100.00));
        empleados.add(new Empleado(2,"Maria", "CEO", new Date(), 20000.00, 100.00));
        empleados.add(new Empleado(3,"Luis", "Marketing", new Date(), 1000.00, 100.00));
        empleados.add(new Empleado(4,"Pablo", "CTO", new Date(), 10000.00, 100.00));
        empleados.add(new Empleado(5,"Marta", "Developer", new Date(), 2000.00, 100.00));
        return empleados;
    }
}
