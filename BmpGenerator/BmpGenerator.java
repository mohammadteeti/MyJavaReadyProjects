package BmpGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import java.awt.Point;

import java.awt.Dimension;

public class BmpGenerator {
    private static volatile boolean exit = false;
    private static int fileNumber= 0;

    public static void main(String[] args) throws IOException {

        if (args.length<3){
            System.out.println("please specify Width Height Depth values !");
            return;
        }

        int w =Integer.parseInt(args[0]);
        int h =Integer.parseInt(args[1]);
        int d = Integer.parseInt(args[2]);
        boolean exit =false;
        
        switch (d){
            case 1: 
            
            if (((w*h)%8)!=0){
                System.out.println("please Change Dimensions to suit the Bit-Depth of 1 \n"+
                "for Bit-DEpth 1 ,Dimensions multiplication shoud devide on 8 with 0 reminder");
                
                exit=true;
            }
        break;

        case 4 : 
            if (((w*h)%2)!=0){
                System.out.println("please Change Dimensions to suit the Bit-Depth of 4 \n"+
                "for Bit-Depth 4 ,Dimensions multiplication shoud devide on 2 with 0 reminder");
                exit=true;
            }
        break;
        case 8 : case 16: case 24: case 32:
        break;
        default:
            System.out.println("Bit Depth should be [1,4,8,16,24,32] ");
            return ;
        }

        if(exit)
            return;
        int width = Integer.parseInt(args[0]);
        int height = Integer.parseInt(args[1]);
        int depth = Integer.parseInt(args[2]);

        ThreadClass myT = new ThreadClass();
        for (int i = 0 ;i<10;i++){
        myT.randomBmp(new Dimension(width,height),depth);
        System.out.print("File Generated at output\\Bitmap_"+fileNumber+".bmp\r");
        }

    }

    private void randomBmp(Dimension d ,int bitDepth) {


        int w = (int) d.getWidth();
        int h = (int) d.getHeight();
        int size; // data size width pixels * height * bytes/pixel
        double depth ;
        switch(bitDepth){
            case 1: 
                depth = 0.125;
                size =w*h;
            break;

            case 4 : 
                depth = 0.5;
                size =w * h;
            break;

            case 8 :
                depth=1;
                size =(int)(w * h * depth);
            break;

            case 16 : 
                depth = 2;
                size =(int)(w * h * depth);
            break;

            case 24 :
                depth=3;
                size =(int)(w * h * depth);
            break;

            case 32 : 
                depth = 4;
                size =(int)(w * h * depth);
            break;

            default:
                System.out.println("Bit Depth should be [1,4,8,16,24,32] ");
                return ;
            }


        int totalSize = size + 54; // data + header size total file size
        byte[] wInBytes = new byte[4];
        byte[] hInBytes = new byte[4];
        byte[] sizeOfdata = new byte[4];
        byte[] fileSize = new byte[4];

        sizeOfdata[0] = (byte) (size & 0xFF);
        sizeOfdata[1] = (byte) ((size >> 8) & 0xFF);
        sizeOfdata[2] = (byte) ((size >> 16) & 0xFF);
        sizeOfdata[3] = (byte) ((size >> 24) & 0xFF);

        fileSize[0] = (byte) (totalSize & 0xFF);
        fileSize[1] = (byte) ((totalSize >> 8) & 0xFF);
        fileSize[2] = (byte) ((totalSize >> 16) & 0xFF);
        fileSize[3] = (byte) ((totalSize >> 24) & 0xFF);

        wInBytes[0] = (byte) (w & 0xFF);
        wInBytes[1] = (byte) ((w >> 8) & 0XFF);
        wInBytes[2] = (byte) ((w >> 16) & 0XFF);
        wInBytes[3] = (byte) ((w >> 24) & 0XFF);

        hInBytes[0] = (byte) (h & 0xFF);
        hInBytes[1] = (byte) ((h >> 8) & 0XFF);
        hInBytes[2] = (byte) ((h >> 16) & 0XFF);
        hInBytes[3] = (byte) ((h >> 24) & 0XFF);

        System.out.print("Generating Header ... \r");
        byte[] header = { 0x42, 0x4D, fileSize[0], fileSize[1], fileSize[2], fileSize[3], 0x00, 0x00, 0x00, 0x00, 0x36,0x00, 0x00, 0x00, 0x28, 0x00,
                          0x00, 0x00, wInBytes[0], wInBytes[1], wInBytes[2], wInBytes[3], hInBytes[0], hInBytes[1], hInBytes[2],hInBytes[3], 0x01, 0x00,(byte) (bitDepth&0xFF), (byte)((bitDepth>>8)&0xFF), 0x00, 0x00,
                          0x00, 0x00, sizeOfdata[0], sizeOfdata[1], sizeOfdata[2], sizeOfdata[3], 0x00, 0x00, 0x00, 0x00, 0x00,0x00, 0x00, 0x00, 0x00, 0x00,
                          0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };

        byte data[] = new byte[size]; // data to be generated
        
        System.out.print("Generating Random Binary ... \r");
        Random r = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) r.nextInt(255);
        }

        System.out.print("Filling Bitmap ...\r");
        byte [] bitmapBytes = new byte[totalSize];
        for (int i = 0; i < 54; i++) {
            bitmapBytes[i]=header[i];
            
        }

        for (int i = 54; i < totalSize; i++) {
            bitmapBytes[i]=data[i-54];
            
        }

        System.out.print("Writing File ... \r");
        File f ;
        FileOutputStream fos=null;
        try{
            f = new File("output\\Bitmap_"+fileNumber+".bmp");
            fos=new FileOutputStream(f);
            fos.write(bitmapBytes);
            fileNumber++;
        }catch(IOException e){
            System.out.println("Error " + e.getMessage());
        }
        finally{
            System.out.print("Closing Stream ...\r");
            try{
            fos.close();}
            catch(IOException e){
                System.out.println(e.getMessage());
            }
            System.out.print("Done!\r");
        }

    }

}
