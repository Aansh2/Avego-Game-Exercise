package com.avego;

import com.avego.game.Playground;

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
 * Author: Ghodrat Naderi
 * E-Mail: Naderi.ghodrat@gmail.com
 * Date  : 5/12/13
 * Time  : 9:58 PM
 */
public class Launcher {
    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        try {


            if (args.length <= 1)
                launcher.launch(30, 30, 10, 2);
            else {
                int x = Integer.parseInt(args[1]);
                int y = Integer.parseInt(args[2]);
                int numberOfPlayer = Integer.parseInt(args[3]);
                int playerPrivateZone = 2;
                if (args.length > 4)
                    playerPrivateZone = Integer.parseInt(args[4]);
                launcher.launch(x, y, numberOfPlayer, playerPrivateZone);
            }
        } catch (Exception ex) {
            launcher.launch(30, 30, 10, 2);
        }
    }

    public void launch(int width, int height, int numberOfPlayer, int privateZone) {
        System.out.println(" ========================Avego Game for Interview===================================  ");
        Playground playground = new Playground(width, height, numberOfPlayer, privateZone);
        Thread thread = new Thread(playground, "Playground_Thread");
        thread.start();
    }
}
