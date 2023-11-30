package kr.blackteam.dice.server.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import kr.blackteam.dice.server.dto.DiceBasicInfoDto;
import kr.blackteam.dice.server.dto.PlayerCreationDto;
import kr.blackteam.dice.server.dto.PlayerEmailUpdateDto;
import kr.blackteam.dice.server.dto.PlayerFullInfoDto;
import kr.blackteam.dice.server.dto.PlayerNameUpdateDto;
import kr.blackteam.dice.server.exception.PlayerNotFoundException;
import kr.blackteam.dice.server.model.Player;
import kr.blackteam.dice.server.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PlayerService {

  private final PlayerRepository playerRepository;

  @Transactional
  public PlayerFullInfoDto createPlayer(PlayerCreationDto request) {
    Player player = Player.builder().name(request.getName()).email(request.getEmail()).build();
    return PlayerFullInfoDto.fromEntity(playerRepository.save(player));
  }

  @Transactional(readOnly = true)
  public List<PlayerFullInfoDto> getAllPlayers() {
    return playerRepository.findAll().stream().map(PlayerFullInfoDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public PlayerFullInfoDto getPlayer(UUID id) {
    Player player = playerRepository.findById(id)
        .orElseThrow(() -> new PlayerNotFoundException(id));
    return PlayerFullInfoDto.fromEntity(player);
  }

  @Transactional(readOnly = true)
  public List<DiceBasicInfoDto> getDices(UUID id) {
    Player player = playerRepository.findById(id)
        .orElseThrow(() -> new PlayerNotFoundException(id));
    return player.getDices().stream().map(DiceBasicInfoDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Transactional
  public PlayerFullInfoDto updatePlayer(PlayerFullInfoDto request) {
    Player player = playerRepository.findById(request.getId())
        .orElseThrow(() -> new PlayerNotFoundException(request.getId()));
    player.setName(request.getName());
    player.setEmail(request.getEmail());
    return PlayerFullInfoDto.fromEntity(playerRepository.save(player));
  }

  @Transactional
  public PlayerFullInfoDto updatePlayer(UUID id, PlayerNameUpdateDto request) {
    Player player = playerRepository.findById(id)
        .orElseThrow(() -> new PlayerNotFoundException(id));
    player.setName(request.getName());
    return PlayerFullInfoDto.fromEntity(playerRepository.save(player));
  }

  @Transactional
  public PlayerFullInfoDto updatePlayer(UUID id, PlayerEmailUpdateDto request) {
    Player player = playerRepository.findById(id)
        .orElseThrow(() -> new PlayerNotFoundException(id));
    player.setEmail(request.getEmail());
    return PlayerFullInfoDto.fromEntity(playerRepository.save(player));
  }

  @Transactional
  public void deletePlayer(UUID id) {
    if (playerRepository.existsById(id)) {
      playerRepository.deleteById(id);
    } else {
      throw new PlayerNotFoundException(id);
    }
  }
}
