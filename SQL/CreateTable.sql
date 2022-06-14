#User(email, name, pwd)
Create Table Users(
      email varchar(50) not null,
      name char(20) not null,
      pwd char(20) not null,
      primary key(email)
);

#Genres(genreID, name, description)
Create Table Genres(
       genreID int auto_increment,
       name char(20) not null,
       description varchar(100),
       primary key(genreID)
);

#Artist(artistID, artistName, follower, description)
Create Table Artists(
        artistID char(50),
        artistName char(20) not null,
        follower int,
        description varchar(100),
        primary key(artistID)
);

#Albums(albumID, albumName, artistID, publishDate)
Create Table Albums(
       albumID char(50),
       albumName char(20) not null,
       artistID char(50) not null,
       publishDate date,
       primary key(albumID),
       foreign key (artistID) references Artists(artistID)
           on delete cascade
           on update no action
);

#Songs(songID, songName, url, popularity, duration, publishDate, albumID, artistID, genreID)
Create Table Songs(
      songID char(50),
      songName char(20) not null,
      url varchar(100),
      popularity int, #like numbers
          duration int, #seconds
          publishDate date,
      albumID char(50),
      artistID char(50),
      genreName char(20),
      primary key(songID),
      foreign key (albumID) references Albums(albumID)
          on delete set null
          on update no action,
      foreign key (artistID) references Artists(artistID)
          on delete set null
          on update no action,
      UNIQUE uniqueSong(songName, artistID)
);

#Favorite(id, userEmail, songId)
Create Table Favorite(
         id int auto_increment,
         userEmail varchar(50) not null,
         songID char(50) not null,
         primary key(id),
         foreign key (userEmail) references Users(email)
             on delete cascade
             on update no action,
         foreign key (songID) references Songs(songID)
             on delete cascade
             on update no action
);

#PlaylistOwnership(playlistID, userEmail)
Create Table PlaylistOwnership(
          playlistID int auto_increment,
          userEmail varchar(50) not null,
          primary key(playlistID),
          foreign key (userEmail) references Users(email)
              on delete cascade
              on update no action
);

#PlaylistSongs(id, playlistID, songID)
#This table stores which songs stores in which playlist
Create Table PlaylistSongs(
          id int auto_increment,
          playlistID int not null,
          songID char(50) not null,
          primary key(id),
          foreign key (playlistID) references PlaylistOwnership(playlistID)
              on delete cascade
              on update no action,
          foreign key (songID) references Songs(songID)
              on delete cascade
              on update no action
);