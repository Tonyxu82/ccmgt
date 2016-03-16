create keyspace freeway with replication={'class':'SimpleStrategy', 'replication_factor':1};

CREATE TABLE freeway.station_data (
 	locationtext text,
 	length float,
 	stationid int,
 	stationclass int,
 	numberlanes int,
 	latlon text,
 	upstream int,
 	downstream int,
 	milepost float,
 	highwayid int,
 	PRIMARY KEY (stationid)
);

CREATE INDEX sindex ON freeway.station_data(locationtext);

CREATE TABLE freeway.loop (
    detectorid int,
    starttime timestamp,
    dqflags int,
    occupancy int,
    speed int,
    status int,
    volume int,
    PRIMARY KEY (detectorid, starttime)
);

CREATE INDEX sindex1 ON freeway.loop(dqflags);

CREATE TABLE freeway.loopmain (
    detectorid int,
    starttime timestamp,
    dqflags int,
    occupancy int,
    speed int,
    status int,
    volume int,
    PRIMARY KEY (detectorid, starttime)
);

CREATE INDEX sindex2 ON freeway.loopmain(dqflags);

CREATE TABLE freeway.detector_data (
    locationtext text,
    detectorid int,
    detectorclass int,
    lanenumber int,
    milepost float,
    stationid int,
    PRIMARY KEY (locationtext, detectorid)
);
