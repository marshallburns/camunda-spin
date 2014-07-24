/* Licensed under the Apache License, Version 2.0 (the "License");
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
package org.camunda.spin;

import java.io.InputStream;

import org.camunda.spin.impl.SpinFactoryImpl;
import org.camunda.spin.spi.DataFormat;

/**
 *
 * @author Sebastian Menski
 * @author Daniel Meyer
 */
public abstract class SpinFactory {

  protected static SpinFactory INSTANCE;

  public static SpinFactory getInstance() {
    if(INSTANCE == null) {
      synchronized (SpinFactory.class) {
        if(INSTANCE == null) {
          INSTANCE = new SpinFactoryImpl();
        }
      }
    }
    return INSTANCE;
  }

  public abstract <T extends Spin<?>> T createSpin(T parameter);
  
  public abstract <T extends Spin<?>> T createSpin(String parameter);
  
  public abstract <T extends Spin<?>> T createSpin(InputStream parameter);

  public abstract <T extends Spin<?>> T createSpin(T parameter, DataFormat<T> format);
  
  public abstract <T extends Spin<?>> T createSpin(String parameter, DataFormat<T> format);

  public abstract <T extends Spin<?> > T createSpin(InputStream parameter, DataFormat<T> format);

  public abstract <T extends Spin<?> > T createSpin(Object parameter, DataFormat<T> format);


}
