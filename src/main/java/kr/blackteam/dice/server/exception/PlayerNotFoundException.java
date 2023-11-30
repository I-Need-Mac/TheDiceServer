package kr.blackteam.dice.server.exception;

import java.util.UUID;

public class PlayerNotFoundException extends RuntimeException {

  public PlayerNotFoundException(UUID id) {
    super("Player not found with ID: " + id);
  }
}
