/*
 * Copyright 2013 © Nick Egorrov, nicegorov@yandex.ru.
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

package abstractgame;

import java.util.EventObject;

public class GameEvent extends EventObject {
    private static final long serialVersionUID = 8955175337453575586L;
    String reason;

	public GameEvent(Object source, String reason) {
		super(source);
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}
}
