/**
 * JBoss, Home of Professional Open Source.
 * Copyright 2014-2020 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
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
package org.jboss.pnc.api.constants;

import static org.jboss.pnc.api.constants.Patterns.PRODUCT_MILESTONE_VERSION;
import static org.jboss.pnc.api.constants.Patterns.PRODUCT_RELEASE_VERSION;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:matejonnet@gmail.com">Matej Lazar</a>
 */
public class PatternsTest {

    @Test
    public void testMilestoneVersionPattern() {
        Pattern pattern = Pattern.compile(PRODUCT_MILESTONE_VERSION);

        Assertions.assertTrue(pattern.matcher("1.2.3.Final").matches());
        Assertions.assertTrue(pattern.matcher("1.2.3.Final_1").matches());
        Assertions.assertTrue(pattern.matcher("1.2.3.Final-1").matches());
        Assertions.assertTrue(pattern.matcher("1.2.3.CR1.CD2").matches());
        Assertions.assertTrue(pattern.matcher("1.2.CR1.CD2").matches());
        Assertions.assertTrue(pattern.matcher("1.2.Final").matches());
        Assertions.assertTrue(pattern.matcher("1.2.3.CR1.CD2.ER1").matches());
        Assertions.assertTrue(pattern.matcher("1.2.3").matches());
        Assertions.assertTrue(pattern.matcher("1.0.0-CD1").matches());
        Assertions.assertTrue(pattern.matcher("1.2.CR1.3").matches());

        Assertions.assertFalse(pattern.matcher("1.CR1").matches());
        Assertions.assertFalse(pattern.matcher("1.0").matches());
        Assertions.assertFalse(pattern.matcher("1.0.").matches());
        Assertions.assertFalse(pattern.matcher("1.3.-").matches());
        Assertions.assertFalse(pattern.matcher("1.2.3.-").matches());
    }

    @Test
    public void testProductReleaseVersionPattern() {
        Pattern pattern = Pattern.compile(PRODUCT_RELEASE_VERSION);

        Assertions.assertTrue(pattern.matcher("1.2.3.Final").matches());
        Assertions.assertTrue(pattern.matcher("1.2.3.Final_1").matches());
        Assertions.assertTrue(pattern.matcher("1.2.3.Final-1").matches());
        Assertions.assertFalse(pattern.matcher("1.2.3.CR1.CD2").matches());
        Assertions.assertFalse(pattern.matcher("1.2.3").matches());
    }
}
