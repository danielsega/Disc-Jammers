/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disc.jammers.boxsprite.players;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.disc.jammers.Constant;
import com.disc.jammers.boxsprite.BoxSprite;
import static com.disc.jammers.Constant.PIXEL_PER_METER;
import static com.disc.jammers.Constant.WIDTH;
import static com.disc.jammers.Constant.HEIGHT;
import com.disc.jammers.entity.Country;
import com.disc.jammers.entity.Level;
import com.disc.jammers.entity.SpecialMoveType;
import com.disc.jammers.event.EventMessage;
import com.disc.jammers.event.EventObject;
import com.disc.jammers.event.EventQueue;
import com.disc.jammers.event.EventType;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author daniel
 */
public class Player extends BoxSprite {

    private boolean isPlayerA;
    private boolean canMove;
    public boolean hasDisc;
    private boolean isSliding;

    private Vector2 destination;
    private Vector2 slidingDest;

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
    private EventObject playerObject;

    private World world;

    private Rectangle upperBound;
    private Rectangle lowerBound;
    private Rectangle midBound;
    private Rectangle playerRec;

    private ShapeRenderer bound;

    public Player(World world, EventQueue queue, AssetManager manager, int player) {
        super(queue, manager);

        destination = new Vector2();
        slidingDest = new Vector2();
        playerObject = new EventObject();

        upperBound = new Table.DebugRect();
        lowerBound = new Table.DebugRect();
        midBound = new Table.DebugRect();
        playerRec = new Table.DebugRect();

        bound = new ShapeRenderer();

        isPlayerA = player == 0;

        this.world = world;
    }

    @Override
    public void handleEvents(EventMessage message) {

        if (message.getMap().containsKey(EventType.MID_BOUNDARY_POSITION)) {
            midBound.x = message.getFloatArray(EventType.MID_BOUNDARY_POSITION)[0];
            midBound.y = message.getFloatArray(EventType.MID_BOUNDARY_POSITION)[1] / 2.5f;
        }
        if (message.getMap().containsKey(EventType.UPPER_BOUNDARY_POSITION)) {
            upperBound.x = message.getFloatArray(EventType.UPPER_BOUNDARY_POSITION)[0] / 4;
            upperBound.y = message.getFloatArray(EventType.UPPER_BOUNDARY_POSITION)[1];
        }
        if (message.getMap().containsKey(EventType.LOWER_BOUNDARY_POSITION)) {
            lowerBound.x = message.getFloatArray(EventType.LOWER_BOUNDARY_POSITION)[0] / 4;
            lowerBound.y = message.getFloatArray(EventType.LOWER_BOUNDARY_POSITION)[1];
        }

        if (message.getMap().containsKey(EventType.MID_BOUNDARY_SIZE)) {
            midBound.width = message.getFloatArray(EventType.MID_BOUNDARY_SIZE)[0] * 2;
            midBound.height = message.getFloatArray(EventType.MID_BOUNDARY_SIZE)[1] * 2;
        }
        if (message.getMap().containsKey(EventType.UPPER_BOUNDARY_SIZE)) {
            upperBound.width = message.getFloatArray(EventType.UPPER_BOUNDARY_SIZE)[0] * 2;
            upperBound.height = message.getFloatArray(EventType.UPPER_BOUNDARY_SIZE)[1] * 2;
        }
        if (message.getMap().containsKey(EventType.LOWER_BOUNDARY_SIZE)) {
            lowerBound.width = message.getFloatArray(EventType.LOWER_BOUNDARY_SIZE)[0] * 2;
            lowerBound.height = message.getFloatArray(EventType.LOWER_BOUNDARY_SIZE)[1] * 2;
        }

        if (isPlayerA) {

            if (message.getMap().containsKey(EventType.PLAYER_A_TOUCH_DOWN)) {
                playerTouchDown(message);
            }
            if (message.getMap().containsKey(EventType.PLAYER_A_TOUCH_UP)) {
                playerTouchUp(message);
            }

            if (message.getMap().containsKey(EventType.PLAYER_A_TOUCH_DRAGGED)) {
                playerTouchDragged(message);
            }

            if (message.getMap().containsKey(EventType.PLAYER_A_HAS_DISC)) {
                hasDiscAction(message);
            }

            /*if (message.getMap().containsKey(EventType.STOP_PLAYER_A)) {
                playerBody.setLinearVelocity(0, 0);
            }*/

        } else {

            if (message.getMap().containsKey(EventType.PLAYER_B_TOUCH_DOWN)) {
                playerTouchDown(message);
            }
            if (message.getMap().containsKey(EventType.PLAYER_B_TOUCH_UP)) {
                playerTouchUp(message);
            }
            if (message.getMap().containsKey(EventType.PLAYER_B_TOUCH_DRAGGED)) {
                playerTouchDragged(message);
            }

            if (message.getMap().containsKey(EventType.PLAYER_B_HAS_DISC)) {
                hasDiscAction(message);
            }

            /*if (message.getMap().containsKey(EventType.STOP_PLAYER_B)) {
                playerBody.setLinearVelocity(0, 0);
            }*/
        }

    }

    @Override
    public void update(float dt) {
        checkCollisionAndMovement(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        bound.begin(ShapeRenderer.ShapeType.Line);
        bound.rect(upperBound.x, upperBound.y, upperBound.width, upperBound.height);
        bound.end();

        bound.begin(ShapeRenderer.ShapeType.Line);
        bound.rect(lowerBound.x, lowerBound.y, lowerBound.width, lowerBound.height);
        bound.end();

        bound.begin(ShapeRenderer.ShapeType.Line);
        bound.rect(midBound.x, midBound.y, midBound.width, midBound.height);
        bound.end();

        bound.begin(ShapeRenderer.ShapeType.Line);
        bound.rect(playerRec.x, playerRec.y, playerRec.width, playerRec.height);
        bound.end();
    }

    @Override
    public void dispose() {
    }

    @Override
    public void init() {

        createPlayerBox(world);

        if (isPlayerA) {
            fixture.setUserData(Constant.PLAYER_A);
        } else {
            fixture.setUserData(Constant.PLAYER_B);
        }

    }

    private void hasDiscAction(EventMessage message) {
        hasDisc = true;
        canMove = false;
        isSliding = false;
        playerObject.setObject(true);
        eventQueue.addEvent(new EventMessage(EventType.STOP_DISC, playerObject));
    }

    private void createPlayerBox(World world) {
        int xSet = WIDTH / 4;
        int ySet = HEIGHT / 2;

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();

        if (!isPlayerA) {
            xSet = WIDTH - xSet;
            ySet = HEIGHT - ySet;

        }

        bodyDef.position.set(xSet / PIXEL_PER_METER, ySet / PIXEL_PER_METER);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        playerBody = world.createBody(bodyDef);
        playerBody.setFixedRotation(true);
        shape.setAsBox(50 / PIXEL_PER_METER, 50 / PIXEL_PER_METER);

        fdef.shape = shape;
        fdef.density = 108f;
        fdef.friction = 100;
        fdef.isSensor = true;
        fdef.filter.categoryBits = Constant.BIT_PLAYERS;
        fdef.filter.maskBits = Constant.BIT_BOUNDARY | Constant.BIT_DISC | Constant.BIT_BOUNDARY_MID;
        fixture = playerBody.createFixture(fdef);

        shape.dispose();
    }

    private void cretePlayerRec() {
        playerRec.x = (playerBody.getPosition().x * PIXEL_PER_METER) - 50;
        playerRec.y = (playerBody.getPosition().y * PIXEL_PER_METER) - 50;
        playerRec.width = 100;
        playerRec.height = 100;
    }

    private void playerTouchDown(EventMessage message) {
        destination.set(message.getIntArray(EventType.PLAYER_A_TOUCH_DOWN)[0] / PIXEL_PER_METER, (((HEIGHT) - message.getIntArray(EventType.PLAYER_A_TOUCH_DOWN)[1])) / PIXEL_PER_METER);
        canMove = true;
    }

    private void playerTouchUp(EventMessage message) {
        isSliding = false;
    }

    private void playerTouchDragged(EventMessage message) {
        slidingDest.set(message.getIntArray(EventType.PLAYER_A_TOUCH_DRAGGED)[0] / PIXEL_PER_METER, message.getIntArray(EventType.PLAYER_A_TOUCH_DRAGGED)[1] / PIXEL_PER_METER);
        isSliding = true;
        canMove = false;
    }

    private void checkCollisionAndMovement(float dt) {
        cretePlayerRec();

        if (playerRec.overlaps(midBound)) {
            playerBody.setLinearVelocity(-.1f, 0);
        } else if (playerRec.overlaps(lowerBound)) {
            playerBody.setLinearVelocity(0, +.1f);
        } else if (playerRec.overlaps(upperBound)) {
            playerBody.setLinearVelocity(0, -.1f);
        } else if (canMove) {
            float x = destination.x - playerBody.getPosition().x;
            float y = destination.y - playerBody.getPosition().y;

            playerBody.setLinearVelocity(getMoveStep(x, y, dt));
        }else if (isSliding){
            float x = destination.x - slidingDest.x;
            float y = destination.y - slidingDest.y;
            
            playerBody.setLinearVelocity(x * -1 ,y);
        }
    }

    private Vector2 getMoveStep(float x, float y, float dt) {

        if (x < 0) {
            x -= dt;
        } else {
            x += dt;
        }

        if (y < 0) {
            y -= dt;
        } else {
            y += dt;
        }

        return new Vector2(x, y);
    }
}
