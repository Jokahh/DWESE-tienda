package com.jokaah.springprojects.tienda.model;

public class DetallePedido {
    private int codigo;
    private Producto producto;
    private int cantidad;
    private double subtotal;

    public DetallePedido() {
    }

    public DetallePedido(int codigo) {
        this.codigo = codigo;
    }

    public DetallePedido(int codigo, Producto producto, int cantidad, double subtotal) {
        this.codigo = codigo;
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + codigo;
        result = prime * result + ((producto == null) ? 0 : producto.hashCode());
        result = prime * result + cantidad;
        long temp;
        temp = Double.doubleToLongBits(subtotal);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        DetallePedido other = (DetallePedido) obj;
        if (codigo != other.codigo)
            return false;
        if (producto == null) {
            if (other.producto != null)
                return false;
        } else if (!producto.equals(other.producto))
            return false;
        if (cantidad != other.cantidad)
            return false;
        if (Double.doubleToLongBits(subtotal) != Double.doubleToLongBits(other.subtotal))
            return false;
        return true;
    }
}
