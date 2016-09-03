package com.avego.game;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

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
 * * Date  : 5/14/13
 * Time  : 5:15 PM
 * ----------------------------------------------------------------------------
 */
public class Referee implements IReferee, Runnable {
    private Player[] players;
    private final int width;
    private final int height;

    public Referee(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public void returnOutPlayerToField(Player player) {
        Random random = new Random();

        if (player.getxCoordinate() > width || player.getxCoordinate() < 0) {
            player.setxCoordinate(random.nextInt(width));

        } else if (player.getyCoordinate() > height || player.getyCoordinate() < 0) {
            player.setyCoordinate(random.nextInt(height));
        }


    }

    public void finePlayer(Player player) {

        player.takeYellowCard();

        System.out.println("Player[" + player.getId() + "] get YELLOW_CARD as penalty!");

        if (player.getNumberOfYellowCard() >= 2) {
            if (!player.isHaveRedCard()) {
                player.setNumberOfYellowCard(0);
                player.setHaveRedCard(true);
                System.out.println("Player[" + player.getId() + "] get RED_CARD as penalty! And kicked out of field for 10 seconds!");
                kickOutPlayer(player);

            } else {
                player.setEjected(true);
                System.err.println("Player[" + player.getId() + "] kicked out from the match!");
                kickOutPlayer(player);
            }
        }

        findWinner();
    }

    public void kickOutPlayer(Player player) {
        Random random = new Random();
        player.waitOutSide();
        player.setxCoordinate(random.nextInt(width));
        player.setyCoordinate(random.nextInt(height));
    }

    public Player findWinner() {
        int wIndex = 0;
        int outPlayer = 0;
        Player winner = null;
        for (int i = 0; i < players.length; i++) {
            if (players[i].isEjected())
                outPlayer++;
            else
                wIndex = i;
        }
        if (outPlayer == players.length - 1) {
            winner = players[wIndex];

        }
        return winner;

    }

    public void run() {
        Player winner = null;
        while (winner == null) {
            winner = findWinner();
        }
        winner.setGameFinished(true);
        System.out.println(" ======================Winner Info====================== ");
        System.out.println("                  ID:  " + winner.getId());
        System.out.println("\n                  Have Red Card:  " + winner.isHaveRedCard());
        System.out.println("\n                  Number of Yellow Card:  " + winner.getNumberOfYellowCard());
        System.out.println("\n                  Is OutSide Of Field:  " + winner.isWaitingMode());
        System.out.println("\n                  Location(X,Y):  (" + winner.getxCoordinate() + "," + winner.getyCoordinate() + ")");


    }

    synchronized public void checkPlayerMove(Player player) {
        returnOutPlayerToField(player);
        for (int i = 0; i < players.length; i++) {
            if (players[i].isEjected() || players[i].isWaitingMode() || players[i].getId() == player.getId()||player.isEjected())
                continue;

            if (Util.haveConflict(player, players[i])) {
                finePlayer(player);
            }
        }
    }


}
