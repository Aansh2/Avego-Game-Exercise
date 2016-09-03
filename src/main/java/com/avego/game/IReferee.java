package com.avego.game;

/**
 * Copyright (c) 2016  Ghodratollah Naderi Darehkat
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * Project:avego
 * Author: Ghodratollah Naderi
 * E-Mail: Naderi.ghodrat@gmail.com
 * Date  : 5/14/13
 * Time  : 5:19 PM
 * ----------------------------------------------------------------------------
 */
public interface IReferee {

    public void finePlayer(Player player);

    public Player findWinner();

    public void checkPlayerMove(Player player);

    public void returnOutPlayerToField(Player player);

}
