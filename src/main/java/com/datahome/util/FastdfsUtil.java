package com.datahome.util;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;


public class FastdfsUtil {

    public static final String LOCK1 = "LOCK1";
    public static final String LOCK2 = "LOCK2";
    public static final String LOCK3 = "LOCK3";
    public static final String LOCK4 = "LOCK4";
    public static final String LOCK5 = "LOCK5";

    public FastdfsUtil() {
        try {
            ClientGlobal.init(FastdfsUtil.class.getResource("/").getPath() + "fastdfs-client.properties");
        } catch (Exception e) {

        }
    }

    public String upload(String babyId, String filePath) {
        String fileName = null;
        switch ((babyId.hashCode() & Integer.MAX_VALUE) % 5) {
            case 0:
                synchronized (LOCK1) {
                    fileName = upload(3, filePath, babyId);
                }
                break;
            case 1:
                synchronized (LOCK2) {
                    fileName = upload(3, filePath, babyId);
                }
                break;
            case 2:
                synchronized (LOCK3) {
                    fileName = upload(3, filePath, babyId);
                }
                break;
            case 3:
                synchronized (LOCK4) {
                    fileName = upload(3, filePath, babyId);
                }
                break;
            case 4:
                synchronized (LOCK5) {
                    fileName = upload(3, filePath, babyId);
                }
                break;
            default:
                System.out.println("0000");
        }
        return fileName;
    }

    public String upload(int retry, String filePath, String babyid) {
        boolean bl = false;
        int count = 1;
        String fileName = "";

        //init
        TrackerServer trackerServer = createTrackerServer();
        if (trackerServer == null) return fileName;
        StorageClient storageClient = createStorageClient(trackerServer);
        if (storageClient == null) return fileName;

        while (!bl && count <= retry) {
            try {
                //参数
                NameValuePair[] metadatas = new NameValuePair[1];
                metadatas[0] = new NameValuePair("babyId", babyid);

                //上传
                String[] results = storageClient.upload_file(filePath, null, metadatas);
                if (results == null) {
                    throw new Exception("上传失败：" + storageClient.getErrorCode());
                }

                //解析结果
                String groupName = results[0];
                fileName = "/" + groupName + "/" + results[1];
                //成功标记
                bl = true;
            } catch (Exception e) {

            } finally {
                count++;
            }
        }
        try {
            trackerServer.close();
        } catch (Exception e) {

        }

        return fileName;
    }

    public void delete(String fileName) {
        //init
        TrackerServer trackerServer = createTrackerServer();
        if (trackerServer == null) return;
        StorageClient storageClient = createStorageClient(trackerServer);
        if (storageClient == null) return;

        boolean bl = false;
        int count = 1;
        if (fileName == null) {
            return;
        }

        while (!bl && count <= 3) {
            try {
                //参数
                String groupName = "group1";

                //删除
                int status = storageClient.delete_file(groupName, fileName.replace("/group1/", ""));
                if (status == 0) {
                    bl = Boolean.TRUE;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                count++;
            }
        }
        try {
            trackerServer.close();
        } catch (Exception e) {

        }
    }

    public TrackerServer createTrackerServer() {
        try {
            TrackerClient trackerClient = new TrackerClient();
            return trackerClient.getConnection();
        } catch (Exception e) {

            return null;
        }
    }

    public StorageClient createStorageClient(TrackerServer trackerServer) {
        try {
            return new StorageClient(trackerServer, null);
        } catch (Exception e) {

            return null;
        }
    }

    public  static void main(String arg[]){
        String upload = new FastdfsUtil().upload("1111111111", "D:\\workspace\\shmec\\shmec_age06_gdnindex\\src\\main\\resources\\ss.PNG");
        System.out.println(upload);
    }
}