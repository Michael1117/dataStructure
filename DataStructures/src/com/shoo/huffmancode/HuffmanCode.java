package com.shoo.huffmancode;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
        /*String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();*/

        //System.out.println(content.length());

        /*List<Node> nodes = getNodes(contentBytes);

        //System.out.println("nodes = " + nodes);
        // 创建霍夫曼树
        System.out.println("霍夫曼树");

        Node huffmanTreeRoot = createHuffmanTree(nodes);
        System.out.println("前序遍历");
        huffmanTreeRoot.preOrder();

        // 测试是否生成了对应的霍夫曼编码
        getCodes(huffmanTreeRoot, "", stringBuilder);

        System.out.println("~~生成的霍夫曼编码表" + huffmanCodes);

        // 测试
        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);

        System.out.println("huffmanCodeBytes =" + Arrays.toString(huffmanCodeBytes));*/

        /*byte[] huffmanCodeBytes = huffmanZip(contentBytes);

        System.out.println("压缩后的结果是：" + Arrays.toString(huffmanCodeBytes) + " 长度 = " + huffmanCodeBytes.length);

        // 测试byteToBitString 方法
        //System.out.println(byteToBitString((byte) -1));
        byte[] sourceBytes = decode(huffmanCodes, huffmanCodeBytes);
        System.out.println("原理的字符串=" + new String(sourceBytes));*/

        // 测试压缩文件
        /*String srcFile = "C:\\Users\\michaelhee\\Desktop\\文件\\cc78121536321934.jpg";

        String dstFile = "d://dst.zip";

        zipFile(srcFile, dstFile);
        System.out.println("压缩文件");*/

        // 解压文件
        String zipFile = "d://dst.zip";
        String dstFile = "d://1.jpg";
        unZipFile(zipFile, dstFile);
        System.out.println("解压成功");
    }

    // 解压方法

    /**
     *
     * @param zipFile
     * @param dstFile
     */
    public static void unZipFile(String zipFile, String dstFile) {
        // 定义文件输入流
        InputStream is = null;
        // 定义一个对象输入流
        ObjectInputStream ois = null;
        // 定义文件的输出流
        OutputStream os = null;

        // 创建文件输入流
        try {
            is = new FileInputStream(zipFile);
            // 创建一个和 is 关联的对象输入流
            ois = new ObjectInputStream(is);

            // 读取byte 数组 huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();

            // 读取霍夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();

            // 解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);

            // 将bytes 数组写入目标文件
            os = new FileOutputStream(dstFile);

            // 写数据到dstFile文件
            os.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    // 编写方法，将一个文件进行压缩

    /**
     * @param srcFile
     * @param dstFile
     */
    public static void zipFile(String srcFile, String dstFile) {
        // 创建输出流
        FileInputStream is = null;
        ObjectOutputStream oos = null;
        OutputStream os = null;
        // 创建文件的输入流
        try {
            is = new FileInputStream(srcFile);
            // 创建一个和源文件 大小一样的 byte 数组
            byte[] b = new byte[is.available()];

            // 读取文件
            is.read(b);
            // 直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            // 创建文件的输出流，存放压缩文件
            os = new FileOutputStream(dstFile);
            // 创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            // 把 霍夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);

            // 以对象流的方式写入 霍夫曼编码 ， 为了以后恢复源文件时使用
            // 把霍夫曼编码写入压缩文件
            oos.writeObject(huffmanCodes);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    // 使用一个方法，将前面的方法封装起来，便于调用

    // 重写先转成 霍夫曼编码对应的二进制的字符串  "1010100010111"

    /**
     * @param huffmanCodes 霍夫曼编码表 map
     * @param huffmanBytes 霍夫曼编码得到的字节数组
     * @return 原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        // 1. huffmanBytes 对应的二进制字符串
        StringBuilder stringBuilder = new StringBuilder();

        // 将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            // 判断是否为最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }

        //System.out.println("霍夫曼字节数组对应的二进制字符串 = " + stringBuilder.toString());
        //return null;
        // 把霍夫曼编码表进行调换，
        Map<String, Byte> map = new HashMap<String, Byte>();

        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        //System.out.println("map = " + map);
        //return null;
        // 创建集合， 存放byte
        List<Byte> list = new ArrayList<>();

        // i 就是索引，扫描stringBuilder
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while (flag) {
                // 1010100010111...
                // 取出一个字符  递增取出 key
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);

                if (b == null) {  // 没有匹配
                    count++;
                } else {
                    // 匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count;   // i 直接移动到 count
        }

        // 把list 中的数据放到byte[] 并返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }

        return b;
    }

    /**
     * 将一个 byte 转成一个二进制的字符串
     *
     * @param flag 是否需要补高位如果是true，需要补，如果是false表示不补
     * @param b
     * @return b对应的二进制的字符串 (按补码返回)
     */
    private static String byteToBitString(boolean flag, byte b) {
        // 使用变量保存 b
        int temp = b;  // 将b 转成int
        // 如果是正数要补高位
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);  // 返回的是temp 对应的二进制补码

        if (flag) {
            //System.out.println("str=" + str);
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    /**
     * @param bytes 原始的字符串对应的字节数组
     * @return 是经过 霍夫曼 编码处理后的字节数组 (压缩后的数组)
     */

    private static byte[] huffmanZip(byte[] bytes) {
        //
        List<Node> nodes = getNodes(bytes);
        // 根据nodes创建的霍夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);

        // 对应的霍夫曼编码(根据 霍夫曼树)
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);

        // 根据生成的霍夫曼编码， 压缩得到压缩后的霍夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);

        return huffmanCodeBytes;
    }

    // 编写一个方法，将字符串对应的byte[] 数组，通过生成的霍夫曼编码，返回一个霍夫曼编码 压缩后的byte[]

    /**
     * @param bytes        原始的字符串对应的byte[]
     * @param huffmanCodes 生成的霍夫曼编码zip
     * @return 返回霍夫曼编码处理后的 byte[]
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();

        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }

        //System.out.println("测试stringBuilder = " + stringBuilder);
        //System.out.println("测试stringBuilder~~~ = " + stringBuilder.toString());

        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        // 创建存储压缩后的byte 数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;  // 记录第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {  // 不够8位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }

            // 将strByte 转成一个 byte, 放入到 huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;

        }

        return huffmanCodeBytes;
    }

    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();

    static StringBuilder stringBuilder = new StringBuilder();

    // 为了调用方便，重载getCodes
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        // 处理root左子树
        getCodes(root.left, "0", stringBuilder);
        // 处理root右子树
        getCodes(root.right, "1", stringBuilder);

        return huffmanCodes;
    }

    /**
     * 功能： 将传入的node结点的所有叶子结点的霍夫曼编码得到，并放入到huffmanCodes集合
     *
     * @param node          传入结点
     * @param code          路径： 左子结点是0，右子结点是1
     * @param stringBuilder 拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);

        stringBuilder2.append(code);

        if (node != null) {
            // 判断当前node 是叶子结点还是非叶子结点
            if (node.data == null) {  // 非叶子结点
                // 向左递归
                getCodes(node.left, "0", stringBuilder2);

                // 向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else {  // 叶子结点
                // 找到每个叶子结点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    // 前序遍历的方法
    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("霍夫曼树为空");
        }
    }

    /**
     * @param bytes 接收字节数组
     * @return List 形式
     */
    private static List<Node> getNodes(byte[] bytes) {
        // 创建ArrayList
        ArrayList<Node> nodes = new ArrayList<Node>();

        // 遍历bytes, 统计 存储每个byte出现的次数 -> map[key, value]
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {  // Map 还没有这个字符数据
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
        // 把每一个键值对转成一个Node对象， 并加入nodes集合
        // 遍历map
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    // 通过 List 创建对应的霍夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            // 排序 从小到大
            Collections.sort(nodes);
            // 取出第一颗最小的二叉树

            Node leftNode = nodes.get(0);

            // 第2小的二叉树
            Node rightNode = nodes.get(1);

            // 创建一颗新的二叉树，根结点没有data，只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            // 将已经处理过的两颗二叉树移除
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            // 将新的二叉树加入到nodes
            nodes.add(parent);
        }
        // 返回的结点
        return nodes.get(0);
    }
}

// 创建Node, 数据 和 权值

class Node implements Comparable<Node> {
    Byte data;   // 存放数据本身， 'a' => 97 ' ' => 32
    int weight;     // 权值，表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    // 从小到大排序
    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    //


    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);

        if (this.left != null) {
            this.left.preOrder();
        }

        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
