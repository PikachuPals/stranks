CREATE TABLE players (
  player_uuid CHAR(36) PRIMARY KEY,
  rank        INT  NOT NULL,
  timeInMillisSinceLastRankUp  LONG NOT NULL
);