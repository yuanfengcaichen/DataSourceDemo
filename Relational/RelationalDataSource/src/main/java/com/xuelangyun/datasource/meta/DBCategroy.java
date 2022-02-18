package com.xuelangyun.datasource.meta;

/** @Author mochen.qy @Date 2022/2/15 16:57 @Description: 数据源类型 */
public interface DBCategroy {
  enum DataCenter implements DBCategroy {
    Hadoop
  }

  enum KV implements DBCategroy {
    Redis
  }

  enum Document implements DBCategroy {
    Mongo,
    Minio
  }

  enum Relation implements DBCategroy {
    Mysql,
    SQLServer,
    Oracle,
    Postgres,
    db2
  }

  enum MicroService implements DBCategroy {
    gRPC
  }

  enum HttpService implements DBCategroy {}

  enum LocalService implements DBCategroy {}
}
