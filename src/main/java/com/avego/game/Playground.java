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
 * Time  : 5:46 PM
 * ----------------------------------------------------------------------------
 */
public class Playground implements Runnable {
    private final int width;
    private final int height;
    private final Player[] players;
    private final Referee referee;

    public Playground(int width, int height, int numberOfPlayer, int privateZone) {
        if (width < 1 || height < 1|numberOfPlayer<=0||privateZone<=0)
            throw new IllegalArgumentException("Invalid input data!");
        this.width = width;
        this.height = height;
        referee = new Referee(width, height);
        players = new Player[numberOfPlayer];
        for (int i = 0; i < numberOfPlayer; i++) {
            players[i] = new Player(i, privateZone, referee);
        }
        referee.setPlayers(players);

    }


    public void run() {
        Thread refereeThread = new Thread(referee,"Referee_Thread");
        refereeThread.start();
        for (int i = 0; i < players.length; i++) {
            Thread playerI = new Thread(players[i],"Player["+i+"]_Thread");
            playerI.start();
        }
    }
}
