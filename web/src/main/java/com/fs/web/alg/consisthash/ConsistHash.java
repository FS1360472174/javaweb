/*
 * ConsistHash.java
 */

package com.fs.web.alg.consisthash;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.List;
/**
 * @author fangzhang
 * 一致性hash算法
 */
public class ConsistHash {
    private TreeMap<Integer, NodeIP> consistMap;

    public ConsistHash() {
        List<NodeIP> nodes = new ArrayList<>();
        nodes.add(new NodeIP("101",10));
        nodes.add(new NodeIP("102",100));
        consistMap  = buildHash(nodes);
    }
    public NodeIP select(NodeIP nodeIP) {
        int val = hash(md5(nodeIP.getHost()),0);
        return consistMap.get(val);
    }

    private TreeMap<Integer, NodeIP> buildHash(List<NodeIP> nodes) {
        final TreeMap<Integer, NodeIP> map = new TreeMap<>();
        for (NodeIP ip : nodes) {
            String address = ip.getHost();
            for (int i = 0; i < 160 / 4; i++) {
                byte[] digest = md5(address + i);
                for (int h = 0; h < 4; h++) {
                    int m = hash(digest, h);
                    map.put(m, ip);
                }
            }
        }
        return map;
    }
    private int hash(byte[] digest, int number) {
        return (((int) (digest[3 + number * 4] & 0xFF) << 24)
                | ((int) (digest[2 + number * 4] & 0xFF) << 16)
                | ((int) (digest[1 + number * 4] & 0xFF) << 8)
                | (digest[number * 4] & 0xFF));
    }
    private byte[] md5(String value) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
        md5.reset();
        byte[] bytes;
        try {
            bytes = value.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
        md5.update(bytes);
        return md5.digest();
    }
}
