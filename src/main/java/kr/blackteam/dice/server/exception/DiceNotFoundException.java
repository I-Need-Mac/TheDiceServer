package kr.blackteam.dice.server.exception;

import java.util.UUID;

public class DiceNotFoundException extends RuntimeException {

  public DiceNotFoundException(UUID id) {
    super("Dice not found with ID: " + id);
  }
}
