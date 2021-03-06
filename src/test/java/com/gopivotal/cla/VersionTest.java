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

package com.gopivotal.cla;

import static org.junit.Assert.assertEquals;

import com.gopivotal.cla.testutil.AbstractTypeTest;
import com.gopivotal.cla.testutil.ComparableTestUtils;
import com.gopivotal.cla.testutil.EqualsAndHashCodeTestUtils;
import com.gopivotal.cla.testutil.ToStringTestUtils;

public final class VersionTest extends AbstractTypeTest<Version> {

    private static final Agreement AGREEMENT = new Agreement(Long.MIN_VALUE, "test-agreement");

    @Override
    protected Version getInstance() {
        return new Version(Long.MIN_VALUE + 1, AGREEMENT, "B", "test-individual-content", "test-corporate-content");
    }

    @Override
    protected Version getInstanceWithNulls() {
        return new Version(null, null, null, null, null);
    }

    @Override
    protected void assertState(Version instance) {
        assertEquals((Long) (Long.MIN_VALUE + 1), instance.getId());
        assertEquals(AGREEMENT, instance.getAgreement());
        assertEquals("B", instance.getName());
        assertEquals("test-individual-content", instance.getIndividualAgreementContent());
        assertEquals("test-corporate-content", instance.getCorporateAgreementContent());
    }

    @Override
    protected void assertEqualsAndHashCode(EqualsAndHashCodeTestUtils<Version> instance, EqualsAndHashCodeTestUtils<Version> instanceWithNulls) {
        // @formatter:off
        instance
            .assertEqual(getInstance())
            .assertNotEqual(new Version(Long.MIN_VALUE + 2, null, null, null, null));

        instanceWithNulls
            .assertEqual(getInstanceWithNulls())
            .assertNotEqual(new Version(Long.MIN_VALUE + 2, null, null, null, null));
        // @formatter:on
    }

    @Override
    protected void assertComparable(ComparableTestUtils<Version> instance) {
        // @formatter:off
        instance
            .assertBefore(
                new Version(null, null, "a", null, null),
                new Version(null, null, "A", null, null))
            .assertEqual(
                new Version(null, null, "b", null, null),
                new Version(null, null, "B", null, null))
            .assertAfter(
                new Version(null, null, "c", null, null),
                new Version(null, null, "C", null, null));
        // @formatter:on
    }

    @Override
    protected void assertToString(ToStringTestUtils instance) {
        instance.assertToString("id", "agreement", "name", "individualAgreementContent", "corporateAgreementContent");
    }

}
