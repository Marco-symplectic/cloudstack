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
package com.cloud.storage.upload;

import com.cloud.agent.api.storage.UploadProgressCommand.RequestType;
import com.cloud.storage.Upload.Status;

public class UploadCompleteState extends UploadInactiveState {

    public UploadCompleteState(UploadListener ul) {
        super(ul);
    }

    @Override
    public String getName() {
        return Status.UPLOADED.toString();

    }

    @Override
    public void onEntry(String prevState, UploadEvent event, Object evtObj) {
        super.onEntry(prevState, event, evtObj);
        if (!prevState.equals(getName())) {
            if (event == UploadEvent.UPLOAD_ANSWER) {
                getUploadListener().scheduleImmediateStatusCheck(RequestType.PURGE);
            }
            getUploadListener().setUploadInactive(Status.UPLOADED);
        }

    }
}
