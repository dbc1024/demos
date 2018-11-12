package com.dbc.user.model;

import java.math.BigDecimal;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author dbc
 * @since 2018-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Morder implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private BigDecimal totalAmount;

    private Integer totalQuantity;

    private Long userId;

    private String status;


}
