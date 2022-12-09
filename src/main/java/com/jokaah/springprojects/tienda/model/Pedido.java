package com.jokaah.springprojects.tienda.model;

import java.util.Date;
import java.util.List;

public class Pedido {
    private int codigo;
    private Cliente cliente;
    private List<DetallePedido> detallePedido;
    private Date fecha;
    private double precioTotal;

    public Pedido() {
    }

    public Pedido(int codigo) {
        this.codigo = codigo;
    }

    public Pedido(int codigo, Cliente cliente, List<DetallePedido> detallePedido, Date fecha, double precioTotal) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.detallePedido = detallePedido;
        this.fecha = fecha;
        this.precioTotal = precioTotal;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetallePedido> getDetallePedido() {
        return detallePedido;
    }

    public void setDetallePedido(List<DetallePedido> detallePedido) {
        this.detallePedido = detallePedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + codigo;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pedido other = (Pedido) obj;
        if (codigo != other.codigo)
            return false;
        return true;
    }

}
