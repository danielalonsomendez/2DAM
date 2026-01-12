using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Weather1.Modelotiempo
{
    public static class WeatherCode
    {
        public static string ConvertirCode(float code)
        {
            switch (code)
            {
                case 0:
                    return "Cloud development not observed or not observable";
                    break;
                case 1:
                    return "Cloud generally dissolving or becoming less developed";
                    break;
                case 2:
                    return "State of sky on the whole unchanged";
                    break;
                case 3:
                    return "Clouds generally forming or developing";
                    break;
                case 4:
                    return "Visibility reduced by smoke, e.g. veldt or forest fires, industrial smoke or volcanic ashes";
                    break;
                case 5:
                    return "Haze";
                    break;
                case 6:
                    return "Widespread dust in suspension in the air, not raised by wind at or near the station at the time of observation";
                    break;
                case 7:
                    return "Dust or sand raised by wind at or near the station at the time of observation";
                    break;
                case 8:
                    return "Well-developed dust or sand whirl(s) seen at or near the station";
                    break;
                case 9:
                    return "Duststorm or sandstorm within sight at the time of observation";
                    break;
                case 10:
                    return "Mist";
                    break;
                case 11:
                    return "Patches of shallow fog or ice fog at the station, whether on land or sea not deeper than about 2 metres on land or 10 metres at sea";
                    break;
                case 12:
                    return "More or less continuous shallow fog or ice fog at the station, whether on land or sea, not deeper than about 2m/land or 10m/sea";
                    break;
                case 13:
                    return "Lightning visible, no thunder heard";
                    break;
                case 14:
                    return "Precipitation within sight, not reaching the ground or the surface of the sea";
                    break;
                case 15:
                    return "Precipitation within sight, reaching the ground or the surface of the sea, but distant, i.e. > 5 km from the station";
                    break;
                case 16:
                    return "Precipitation within sight, reaching the ground or the surface of the sea, near to, but not at the station";
                    break;
                case 17:
                    return "Thunderstorm, but no precipitation at the time of observation";
                    break;
                case 18:
                    return "Squalls at or within sight of the station during the preceding hour or at the time of observation";
                    break;
                case 19:
                    return "Funnel clouds at or within sight of the station during the preceding hour or at the time of observation";
                    break;
                case 20:
                    return "Drizzle (not freezing) or snow grains, not falling as showers, during the preceding hour but not at the time of observation";
                    break;

                case 21:
                    return "Rain (not freezing), not falling as showers, during the preceding hour but not at the time of observation";
                    break;
                case 22:
                    return "Snow, not falling as showers, during the preceding hour but not at the time of observation";
                    break;
                case 23:
                    return "Rain and snow or ice pellets, not falling as showers; during the preceding hour but not at the time of observation";
                    break;
                case 24:
                    return "Freezing drizzle or freezing rain; during the preceding hour but not at the time of observation";
                    break;
                case 25:
                    return "Shower(s) of rain during the preceding hour but not at the time of observation";
                    break;
                case 26:
                    return "Shower(s) of snow, or of rain and snow during the preceding hour but not at the time of observation ";
                    break;
                case 27:
                    return "Shower(s) of hail, or of rain and hail during the preceding hour but not at the time of observation";
                    break;
                case 28:
                    return "Fog or ice fog during the preceding hour but not at the time of observation ";
                    break;
                case 29:
                    return "Thunderstorm (with or without precipitation) during the preceding hour but not at the time of observation ";
                    break;
                case 30:
                    return "Slight or moderate duststorm or sandstorm - has decreased during the preceding hour ";
                    break;

                case 31:
                    return " Slight or moderate duststorm or sandstorm - no appreciable change during ";
                    break;
                case 32:
                    return " Slight or moderate duststorm or sandstorm - has begun or has increased ";
                    break;
                case 33:
                    return " Severe duststorm or sandstorm - has decreased during the preceding hour                                                          ";
                    break;

                case 34:
                    return " Severe duststorm or sandstorm - no appreciable change during the preceding hour";
                    break;
                case 35:
                    return " Severe duststorm or sandstorm - has begun or has increased during the ";
                    break;

                case 36:
                    return " Slight/moderate drifting snow - generally low (below eye level)         ";
                    break;
                case 37:
                    return " Heavy drifting snow - generally low (below eye level)";
                    break;

                case 38:
                    return " Slight/moderate blowing snow - generally high (above eye level)";
                    break;
                case 39:
                    return " Heavy blowing snow - generally high (above eye level)  ";
                    break;

                case 40:
                    return "Fog or ice fog at a a distance at the time of observation, but not at station during the preceding hour, the fog or ice fog extending to a level above that of  the observer";
                    break;

                case 41:
                    return "Fog or ice fog in patches";
                    break;
                case 42:
                    return "Fog / ice fog, sky visible, has become thinner during the preceding hour";
                    break;
                case 43:
                    return "Fog / ice fog, sky invisible, has become thinner during the preceding hour";
                    break;
                case 44:
                    return "Fog or ice fog, sky visible, no appreciable change during the past hour";
                    break;
                case 45:
                    return "Fog or ice fog, sky invisible, no appreciable change during the preceding hour";
                    break;
                case 46:
                    return "Fog or ice fog, sky visible, has begun or has become thicker during preceding hour";
                    break;
                case 47:
                    return "Fog or ice fog, sky invisible, has begun or has become thicker during the preceding hour";
                    break;
                case 48:
                    return "Fog, depositing rime, sky visible";
                    break;
                case 49:
                    return "Fog, depositing rime, sky invisible";
                    break;
                case 50:
                    return "DRIZZLE";
                    break;
                case 51:
                    return "Drizzle, not freezing, slight";
                    break;
                case 52:
                    return "Drizzle, not freezing, moderate";
                    break;
                case 53:
                    return "Drizzle, not freezing, heavy";
                    break;
                case 54:
                    return "Drizzle, freezing, slight";
                    break;
                case 55:
                    return "Drizzle, freezing, moderate";
                    break;
                case 56:
                    return "Drizzle, freezing, heavy";
                    break;
                case 57:
                    return "Drizzle and rain, slight";
                    break;
                case 58:
                    return "Drizzle and rain, moderate or heavy";
                    break;
                case 59:
                    return "Reserved";
                    break;
                case 60:
                    return "Rain.";
                case 61:
                    return "Rain, not freezing, slight.";
                case 62:
                    return "Rain, not freezing, moderate.";
                case 63:
                    return "Rain, not freezing, heavy.";
                case 64:
                    return "Rain, freezing, slight.";
                case 65:
                    return "Rain, freezing, moderate.";
                case 66:
                    return "Rain, freezing, heavy.";
                case 67:
                    return "Rain (or drizzle) and snow, slight.";
                case 68:
                    return "Rain (or drizzle) and snow, moderate or heavy.";
                case 69:
                    return "Reserved.";
                case 70:
                    return "SNOW";
                    break;
                case 71:
                    return "Snow, slight";
                    break;
                case 72:
                    return "Snow, moderate";
                    break;
                case 73:
                    return "Snow, heavy";
                    break;
                case 74:
                    return "Ice pellets, slight";
                    break;
                case 75:
                    return "Ice pellets, moderate";
                    break;
                case 76:
                    return "Ice pellets, heavy";
                    break;
                case 77:
                    return "Snow grains";
                    break;
                case 78:
                    return "Ice crystals";
                    break;
                case 79:
                    return "Reserved";
                    break;
                case 80:
                    return "SHOWER(S) or INTERMITTENT PRECIPITATION";
                    break;
                case 81:
                    return "Rain shower(s) or intermittent rain, slight";
                    break;
                case 82:
                    return "Rain shower(s) or intermittent rain, moderate";
                    break;
                case 83:
                    return "Rain shower(s) or intermittent rain, heavy";
                    break;
                case 84:
                    return "Rain shower(s) or intermittent rain, violent";
                    break;
                case 85:
                    return "Snow shower(s) or intermittent snow, slight";
                    break;
                case 86:
                    return "Snow shower(s) or intermittent snow, moderate";
                    break;
                case 87:
                    return "Snow shower(s) or intermittent snow, heavy";
                    break;
                case 88:
                    return "Reserved";
                    break;
                case 89:
                    return "Hail";
                    break;
                case 90:
                    return "THUNDERSTORM";
                    break;
                case 91:
                    return "Thunderstorm, slight or moderate, with no precipitation";
                    break;
                case 92:
                    return "Thunderstorm, slight or moderate, with rain showers and/or snow showers";
                    break;
                case 93:
                    return "Thunderstorm, slight or moderate, with hail";
                    break;
                case 94:
                    return "Thunderstorm, heavy, with no precipitation";
                    break;
                case 95:
                    return "Thunderstorm, heavy, with rain showers and/or snow showers";
                    break;
                case 96:
                    return "Thunderstorm, heavy, with hail";
                    break;
                case 97:
                    return "Reserved";
                    break;
                case 98:
                    return "Reserved";
                    break;
                case 99 or 100:
                    return "Tornado";
                    break;
                default:
                    return "Unknow";
                    break;
            }
        }
    }

}
