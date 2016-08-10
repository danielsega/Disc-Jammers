/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers.boxdisplay;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.disc.jammers.Constant;
import static com.disc.jammers.Constant.PIXEL_PER_METER;
import static com.disc.jammers.Constant.WIDTH;
import static com.disc.jammers.Constant.HEIGHT;
import com.disc.jammers.entity.Country;
import com.disc.jammers.entity.Level;
import com.disc.jammers.entity.SpecialMoveType;
import com.disc.jammers.event.EventMessage;
import com.disc.jammers.event.EventQueue;
import com.disc.jammers.event.EventType;

/**
 *
 * @author daniel
 */
public class Player extends BoxDisplay {

    private boolean isPlayerA;
    private boolean canMove;
    public boolean hasDisc;

    private Vector2 destination;

    protected String firstName;
    protected String lastName;

    protected Country country;

    protected Level level;

    protected int throwingSpeed;
    protected int curvePower;
    protected int slidePower;

    protected int dexterity;

    protected int overallSpeed;
    protected int overallPower;

    public SpecialMoveType special;

    private Body playerBody;

    public Player(World world, EventQueue queue, AssetManager manager) {
        super(queue, manager);
        createPlayerBox(world);
        destination = new Vector2();
    }

    @Override
    public void handleEvents(EventMessage message) {
        if (message.getMap().containsKey(EventType.TOUCH_DOWN)) {
            destination.set(message.getIntArray(EventType.TOUCH_DOWN)[0] / PIXEL_PER_METER, (((HEIGHT) - message.getIntArray(EventType.TOUCH_DOWN)[1])) / PIXEL_PER_METER);

            canMove = true;
        }

        if (message.getMap().containsKey(EventType.TOUCH_UP)) {
            
        }

        if (message.getMap().containsKey(EventType.PLAYER_A_HAS_DISC)) {
            if (isPlayerA) {
                hasDiscAction(message);
            }
        }

        if (message.getMap().containsKey(EventType.PLAYER_B_HAS_DISC)) {
            if (!isPlayerA) {
                hasDiscAction(message);
            }
        }
    }

    @Override
    public void update(float dt) {
        if (canMove) {
            playerBody.setTransform(new Vector2(playerBody.getTransform().getPosition()).interpolate(destination, dt * .5f, Interpolation.linear), playerBody.getAngle());
        }
    }

    @Override
    public void render(SpriteBatch sb) {
    }

    @Override
    public void dispose() {
    }

    @Override
    public void init() {

    }

    public void setPlayerA(boolean value) {
        isPlayerA = value;
    }

    private void hasDiscAction(EventMessage message) {
        hasDisc = true;
        canMove = false;
        eventQueue.addEvent(new EventMessage(EventType.STOP_DISC, true));
    }

    private void createPlayerBox(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set((WIDTH / 4) / PIXEL_PER_METER, (Constant.HEIGHT / 4) / PIXEL_PER_METER);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        playerBody = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50 / PIXEL_PER_METER, 50 / PIXEL_PER_METER);
        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = 1f;
        fdef.filter.categoryBits = Constant.BIT_PLAYERS;
        fdef.filter.maskBits = Constant.BIT_BOUNDARY | Constant.BIT_DISC | Constant.BIT_BOUNDARY_MID;
        fixture = playerBody.createFixture(fdef);

        shape.dispose();
    }

    private void moveBody(int[] coords, float dt) {

    }
}
