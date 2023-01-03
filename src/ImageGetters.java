import fm.last.musicbrainz.coverart.CoverArt;
import fm.last.musicbrainz.coverart.CoverArtArchiveClient;
import fm.last.musicbrainz.coverart.CoverArtImage;
import fm.last.musicbrainz.coverart.impl.DefaultCoverArtArchiveClient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;


public class ImageGetters{
    public ImageGetters() {
    }

    //created a mbid to image url converter based off of the image getter example provided by musicbrainz
    public String imageReturn (String ID) {
        CoverArtArchiveClient client = new DefaultCoverArtArchiveClient();
        String imageURL = "";
            if( !(ID.equals("")) ){
                UUID mbid = UUID.fromString(ID);
                CoverArt coverArt;

                //try {
                coverArt = client.getByMbid(mbid);
                if (coverArt == null){
                    return "null";
                }
                if (coverArt != null) {
                    Iterator var4 = coverArt.getImages().iterator();

                    while (var4.hasNext()) {
                        String s = var4.next().toString();

                        System.out.println(s);
                        System.out.println();

                        if (s.contains("front=true")) {
                            int startingIndex = s.indexOf("http");
                            imageURL = s.substring(startingIndex, s.length() - 1);
                            return imageURL;
                        }
                    }
                }
                //}
                //catch (Exception var7) {
                //System.out.println("Nothing happened");
                //}
            }
            else {
                imageURL = "";
            }
            return imageURL;
    }







//    public ArrayList<String> imageReturn (ArrayList<String> IDs) {
//        CoverArtArchiveClient client = new DefaultCoverArtArchiveClient();
//        ArrayList<String> urlCollection = new ArrayList<>();
//        for (String ID: IDs){
//            if( !(ID.equals("")) ){
//                UUID mbid = UUID.fromString(ID);
//                CoverArt coverArt = null;
//
//                //try {
//                    coverArt = client.getByMbid(mbid);
//                    if (coverArt != null) {
//                        Iterator var4 = coverArt.getImages().iterator();
//
//                        while (var4.hasNext()) {
//                            String s = var4.next().toString();
//                            if (s.contains("front=true")) {
//                                int startingIndex = s.indexOf("http");
//                                String url = s.substring(startingIndex, s.length() - 1);
//                                urlCollection.add(url);
//                            }
//                        }
//                    }
//                //}
//                //catch (Exception var7) {
//                //System.out.println("Nothing happened");
//                //}
//            }
//            else {
//                urlCollection.add("");
//            }
//        }
//        return urlCollection;
//    }



}
