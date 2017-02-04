CREATE TABLE players (
  player_uuid CHAR(36) PRIMARY KEY,
  rank        INT  NOT NULL,
  lastRankUp  LONG NOT NULL
);