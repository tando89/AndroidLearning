package com.tando.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

import sun.rmi.runtime.Log;

public class FlappyBird extends ApplicationAdapter {
	SpriteBatch batch;
	//image background
	Texture background;

	Texture[] birds;
	//flip between bird and bird 2
	int flapState = 0;
	float birdY = 0;
	//how fast the bird will be moving
	float velocity = 0;
	//track the sate of game
	int gameState = 0;
	float gravity = 2;

	//Tubes
	Texture topTube;
	Texture bottomTube;

	//The gap between tubes  can make the game easier or harder
	float gap = 400; //1000 would be super easy
	//The distance of the tubes up/down
	float maxTubeOffset;
	//Generate random gaps
	Random randomGenerator;

	float tubeVelocity = 4;
	int numberOfTubes = 4;
	float[] tubeX = new float[numberOfTubes];
	float[] tubeOffset = new float[numberOfTubes];
	float distanceBetweenTubes;
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("bg.png");

		birds = new Texture[2];
		birds[0] = new Texture("bird.png");
		birds[1] = new Texture("bird2.png");
		//start height position
		birdY = Gdx.graphics.getHeight() / 2 - birds[flapState].getHeight();

		topTube = new Texture("toptube.png");
		bottomTube = new Texture("bottomtube.png");

		maxTubeOffset = Gdx.graphics.getHeight() / 2 - gap / 2 - 100;

		randomGenerator = new Random();

		distanceBetweenTubes = Gdx.graphics.getWidth() * 3 / 4; // / 2 only will be hard

		for (int i = 0; i < numberOfTubes; i++) {

			tubeOffset[i] = (randomGenerator.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap - 200);

			tubeX[i] = Gdx.graphics.getWidth() / 2 - topTube.getWidth() / 2 + i * distanceBetweenTubes;

		}

	}

	@Override
	public void render () {
		batch.begin();
		//display the bg first
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//start processing when user touches the screen
		if (gameState != 0) {
			if (Gdx.input.justTouched()) {
				Gdx.app.log("tapped", "yep!");

				//gameState = 1;
				// the height of the bird when tap
				velocity = -30;


			}

			//display the tube

			for (int i = 0; i < numberOfTubes; i++) {

				if (tubeX[i] < - topTube.getWidth()) {

					tubeX[i] += numberOfTubes * distanceBetweenTubes;

				} else {

					tubeX[i] = tubeX[i] - tubeVelocity;

				}

				batch.draw(topTube, tubeX[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tubeOffset[i]);
				batch.draw(bottomTube, tubeX[i], Gdx.graphics.getHeight() / 2 - gap / 2 - bottomTube.getHeight() + tubeOffset[i]);

			}

			//bird responds to taps

			if (birdY > 0 || velocity < 0) {
				//increase the velocity each time the render loop called
				velocity = velocity + gravity;
				//decrease the position of the bird by the velocity (fall faster)
				birdY -= velocity;

			}
		} else {
			if (Gdx.input.justTouched()) {

				gameState = 1;
			}
		}
		if (flapState == 0) {
			flapState = 1;
		} else {
			flapState = 0;
		}

		//display the bird at the centen of screen lastly
		batch.draw(birds[flapState], Gdx.graphics.getWidth() / 2 - birds[flapState].getWidth(), birdY);
		batch.end();
	}
	


}
