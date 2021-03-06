/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gopivotal.cla.github;

import java.util.Map;

@SuppressWarnings("rawtypes")
final class StandardOrganization extends AbstractGitHubType<Map> implements Organization {

    private volatile String name;

    private volatile Repositories repositories;

    StandardOrganization(Map raw) {
        super(getString("url", raw), Map.class);
        initialize(raw);
    }

    @Override
    void initialize(Map raw) {
        this.name = getString("login", raw);
        this.repositories = new StandardRepositories(getString("repos_url", raw));
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Repositories getRepositories() {
        return this.repositories;
    }

    @Override
    public int compareTo(Organization o) {
        return this.name.compareToIgnoreCase(o.getName());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + this.name.toLowerCase().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Organization)) {
            return false;
        }
        Organization other = (Organization) obj;
        if (!this.name.equalsIgnoreCase(other.getName())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StandardOrganization [name=" + this.name + ", repositories=" + this.repositories + "]";
    }

}
