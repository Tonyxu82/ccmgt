use freeway;
CREATE TABLE Station_data (
 locationtext text,
 length float,
 stationid int  PRIMARY KEY,
 stationclass int,
 numberlanes int,
 latlon text,
 upstream int,
 downstream int,
 milepost float,
 highwayid int
);