/**
 * Copyright (C) FuseSource, Inc.
 * http://fusesource.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fusesource.patch.impl;

import java.util.Collection;

import org.fusesource.patch.Patch;
import org.fusesource.patch.Result;

public class PatchImpl implements Patch {

    private final ServiceImpl service;
    private final String id;
    private final String description;
    private final Collection<String> bundles;
    private Result result;

    public PatchImpl(ServiceImpl service, String id, String description, Collection<String> bundles) {
        this.service = service;
        this.id = id;
        this.description = description;
        this.bundles = bundles;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Collection<String> getBundles() {
        return bundles;
    }

    public boolean isInstalled() {
        return result != null;
    }

    @Override
    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public Result simulate() {
        return service.install(this, true);
    }

    @Override
    public Result install() {
        return service.install(this, false);
    }

    @Override
    public void rollback(boolean force) {
        service.rollback(this, force);
    }

}
