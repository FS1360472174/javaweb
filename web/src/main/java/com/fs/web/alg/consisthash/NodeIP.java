/*
 * NodeIP.java
 */

package com.fs.web.alg.consisthash;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author fangzhang
 *
 */
@Data
@AllArgsConstructor
public class NodeIP {
    private String host;
    private int weight;
}
