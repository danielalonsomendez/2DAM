using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Weather1.Modelogeoapi
{
 

        public class CityData
    {
            public Result[] results { get; set; }
            public Query query { get; set; }
        }

        public class Query
        {
            public string text { get; set; }
            public Parsed parsed { get; set; }
        }

        public class Parsed
        {
            public string city { get; set; }
            public string expected_type { get; set; }
        }

        public class Result
        {
            public Datasource datasource { get; set; }
            public Other_Names other_names { get; set; }
            public string country { get; set; }
            public string country_code { get; set; }
            public string state { get; set; }
            public string county { get; set; }
            public string city { get; set; }
            public string iso3166_2 { get; set; }
            public string iso3166_2_sublevel { get; set; }
            public float lon { get; set; }
            public float lat { get; set; }
            public string state_code { get; set; }
            public string result_type { get; set; }
            public string county_code { get; set; }
            public string NUTS_3 { get; set; }
            public string formatted { get; set; }
            public string address_line1 { get; set; }
            public string address_line2 { get; set; }
            public string category { get; set; }
            public Timezone timezone { get; set; }
            public string plus_code { get; set; }
            public string plus_code_short { get; set; }
            public Rank rank { get; set; }
            public string place_id { get; set; }
            public Bbox bbox { get; set; }
            public string region { get; set; }
            public string state_district { get; set; }
        }

        public class Datasource
        {
            public string sourcename { get; set; }
            public string attribution { get; set; }
            public string license { get; set; }
            public string url { get; set; }
        }

        public class Other_Names
        {
            public string namean { get; set; }
            public string namear { get; set; }
            public string namebe { get; set; }
            public string namebg { get; set; }
            public string namebr { get; set; }
            public string namece { get; set; }
            public string namecy { get; set; }
            public string nameel { get; set; }
            public string nameeu { get; set; }
            public string namefa { get; set; }
            public string namehe { get; set; }
            public string namehy { get; set; }
            public string nameja { get; set; }
            public string nameka { get; set; }
            public string namekk { get; set; }
            public string nameko { get; set; }
            public string namela { get; set; }
            public string namemn { get; set; }
            public string namemr { get; set; }
            public string nameos { get; set; }
            public string namept { get; set; }
            public string nameru { get; set; }
            public string namesr { get; set; }
            public string nameth { get; set; }
            public string nameuk { get; set; }
            public string namezh { get; set; }
            public string nameckb { get; set; }
            public string nameext { get; set; }
            public string namemhr { get; set; }
            public string namepms { get; set; }
            public string namexmf { get; set; }
            public string nameam { get; set; }
            public string nameba { get; set; }
            public string namebn { get; set; }
            public string namecv { get; set; }
            public string nameky { get; set; }
            public string namemk { get; set; }
            public string nameml { get; set; }
            public string namesa { get; set; }
            public string nameta { get; set; }
            public string nametg { get; set; }
            public string namett { get; set; }
            public string nameug { get; set; }
            public string nameur { get; set; }
            public string nameyi { get; set; }
            public string namezhHans { get; set; }
            public string namezhHant { get; set; }
        }

        public class Timezone
        {
            public string name { get; set; }
            public string offset_STD { get; set; }
            public int offset_STD_seconds { get; set; }
            public string offset_DST { get; set; }
            public int offset_DST_seconds { get; set; }
            public string abbreviation_STD { get; set; }
            public string abbreviation_DST { get; set; }
        }

        public class Rank
        {
            public float importance { get; set; }
            public float popularity { get; set; }
            public int confidence { get; set; }
            public int confidence_city_level { get; set; }
            public string match_type { get; set; }
        }

        public class Bbox
        {
            public float lon1 { get; set; }
            public float lat1 { get; set; }
            public float lon2 { get; set; }
            public float lat2 { get; set; }
        }

    
}
