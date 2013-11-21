// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.
package com.cloud.storage.template;

public interface TemplateDownloader extends Runnable {

    /**
     * Callback used to notify completion of download
     *
     */
    public interface DownloadCompleteCallback {
        void downloadComplete(Status status);

    }

    public static enum Status {
        UNKNOWN, NOT_STARTED, IN_PROGRESS, ABORTED, UNRECOVERABLE_ERROR, RECOVERABLE_ERROR, DOWNLOAD_FINISHED, POST_DOWNLOAD_FINISHED
    }

    public static long DEFAULT_MAX_TEMPLATE_SIZE_IN_BYTES = 50L * 1024L * 1024L * 1024L;

    /**
     * Initiate download, resuming a previous one if required
     * @param resume resume if necessary
     * @param callback completion callback to be called after download is complete
     * @return bytes downloaded
     */
    public long download(boolean resume, DownloadCompleteCallback callback);

    /**
     * @return
     */
    public boolean stopDownload();

    /**
     * @return percent of file downloaded
     */
    public int getDownloadPercent();

    /**
     * Get the status of the download
     * @return status of download
     */
    public TemplateDownloader.Status getStatus();

    /**
     * Get time taken to download so far
     * @return time in seconds taken to download
     */
    public long getDownloadTime();

    /**
     * Get bytes downloaded
     * @return bytes downloaded so far
     */
    public long getDownloadedBytes();

    /**
     * Get the error if any
     * @return error string if any
     */
    public String getDownloadError();

    /** Get local path of the downloaded file
     * @return local path of the file downloaded
     */
    public String getDownloadLocalPath();

    public void setStatus(TemplateDownloader.Status status);

    public void setDownloadError(String string);

    public void setResume(boolean resume);

    public boolean isInited();

    public long getMaxTemplateSizeInBytes();

}
