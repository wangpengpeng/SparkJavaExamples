/**
  * Copyright (C) 2015 Baifendian Corporation
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  * http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */
package org.apache.spark.examples.practice.sql

import java.io.File
import java.text.SimpleDateFormat
import java.util.{Date, Properties}

import com.mongodb.casbah.MongoClient
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FSDataOutputStream, FileSystem, Path}
import org.apache.log4j.Logger
import org.apache.spark.examples.practice.streaming.HdfsConnection._

class MongoDBConnection(host: String, port: Int, db: String, collection: String) {
  val logger = Logger.getLogger(getClass.getName)

  def this() = {
    this(Params.mongodbHost, Params.mongodbPort, Params.mongodbDB, Params.mongodbCollection)
  }

  val mongoClient = MongoClient(host, port)

  val dbConn = mongoClient(db)
  val collConn = dbConn(collection)
}

object MongoDBConnection {
  @volatile private var instance: MongoDBConnection = null

  def getInstance: MongoDBConnection = {
    if (instance == null) {
      synchronized {
        if (instance == null) {
          instance = new MongoDBConnection
        }
      }
    }

    instance
  }
}