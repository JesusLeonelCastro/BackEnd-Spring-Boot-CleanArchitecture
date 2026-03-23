package com.sistema.Inventario.DTOs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO para la creación y actualización de usuarios
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductoDTO {

    @NotBlank(message = "El código es obligatorio")
    @Size(max = 50, message = "Máximo 50 caracteres")
    private String codigo;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "Máximo 50 caracteres")
    private String nombre;

    private String descripcion;

    @NotNull(message = "El precio de compra es obligatorio")
    private Double precioCompra;

    @NotNull(message = "El precio de venta es obligatorio")
    private Double precioVenta;

    @NotNull(message = "El stock es obligatorio")   
    private Integer stock;

    @NotNull(message = "El stock mínimo es obligatorio")
    private Integer stockMinimo;

    @NotNull(message = "La categoría es obligatoria")
    private Long categoriaId; // ID de la categoría asociada
}
