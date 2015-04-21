package com.liodevel.my_reader.Utils;

import com.liodevel.my_reader.Models.ItemCollection;

import java.util.ArrayList;

/**
 * Created by emilio on 21/02/2015.
 */
public class StaticCollections {


    // ART

    static ArrayList<ItemCollection> collectionArt = new ArrayList<>();

    public static void updateCollectionArt() {
        collectionArt.add(new ItemCollection("My Modern Metropolis", "http://www.mymodernmet.com/profiles/blog/feed?promoted=1&xn_auth=no", "Where art enthusiasts and trendspotters connect over creative ideas.", "Art", false));
        collectionArt.add(new ItemCollection("Open Culture", "http://www.openculture.com/feed", "A blog that connects to educational and cultural media", "Art", false));
        collectionArt.add(new ItemCollection("Enpundit", "http://feeds.feedburner.com/enpundit", "Cool design, innovative technology, and mind-blowing art", "Art", false));
        collectionArt.add(new ItemCollection("Animal", "http://animalnewyork.com/feed/", "A daily mix of art, news, culture, politics, and opinion — straight from the gut of New York", "Art", false));
        collectionArt.add(new ItemCollection("Hyperallergic", "http://feeds.hyperallergic.com/hyperallergic", "A forum for serious, playful and radical thinking about art in the world today", "Art", false));
        collectionArt.add(new ItemCollection("The Creators Project", "http://thecreatorsproject.vice.com/en_us/rss", "Exclusive interviews, events, and video portraits surrounding the impact of technology in music, art, film, and design", "Art", false));
        collectionArt.add(new ItemCollection("Fast Company Create", "http://www.fastcocreate.com/rss.xml", "The world's leading progressive business media brand, with a unique editorial focus on innovation in technology, ethonomics (ethical economics), leadership, and design", "Art", false));
        collectionArt.add(new ItemCollection("Colossal", "http://feeds.feedburner.com/colossal", "Art and visual ingenuity", "Art", false));
        collectionArt.add(new ItemCollection("Faith is Torment", "http://feeds.feedburner.com/FaithIsTorment", "A curated online art and design blog featuring established and emerging artists and designers working in the visual and applied arts", "Art", false));
        collectionArt.add(new ItemCollection("Lost At E Minor", "http://feeds.feedburner.com/LostAtEMinor", "A publication of trends and pop culture - art, design, music, photography, tech, fashion, and more", "Art", false));
        collectionArt.add(new ItemCollection("Rhizome", "http://feeds.feedburner.com/rhizome-fp", "Dedicated to the creation, presentation, preservation, and critique of emerging artistic practices that engage technology", "Art", false));
        collectionArt.add(new ItemCollection("Bomb Magazine", "http://bombsite.com/feed", "Art & literary quarterly delivering the artist’s voice since 1981. Interviews, fiction, poetry, photography, readings, parties, concerts, contests & more", "Art", false));
    }

    // BOOKS
    static ArrayList<ItemCollection> collectionBooks = new ArrayList<>();

    public static void updateCollectionBooks() {
        collectionBooks.add(new ItemCollection("The New York Review of Books", "http://feeds.feedburner.com/nybooks", "The online version of the biweekly book review and journal of intellectual currents", "Books", false));
        collectionBooks.add(new ItemCollection("New Yorker's Page-Turner", "http://www.newyorker.com/online/blogs/books/rss.xml", "Criticism, contention, and conversation about books that matter", "Books", false));
        collectionBooks.add(new ItemCollection("The Millions", "http://feeds.feedburner.com/themillionsblog/fedw", "An online magazine offering coverage on books, arts, and culture since 2003", "Books", false));
        collectionBooks.add(new ItemCollection("Flavorwire", "http://flavorwire.com/feed", "A network of culturally connected people, covering events, art, books, music, and pop culture the world over", "Books", false));
        collectionBooks.add(new ItemCollection("The Guardian Books", "http://feeds.guardian.co.uk/theguardian/books/rss", "Latest books and literature news, reviews, comment, features and author interviews from the Guardian, the world's leading liberal voice", "Books", false));
        collectionBooks.add(new ItemCollection("London Review of Books", "http://cdn.lrb.co.uk/feeds/lrb", "Literary review publishing essay-length book reviews and topical articles on politics, literature, history, philosophy, science and the arts by leading writers", "Books", false));
        collectionBooks.add(new ItemCollection("The Paris Review", "http://feeds.feedburner.com/TheParisReviewBlog", "A literary magazine featuring original writing, art, and in-depth interviews with famous writers", "Books", false));
        collectionBooks.add(new ItemCollection("The Rumpus", "http://therumpus.net/feed/", "A place where people come to be themselves through their writing, to tell their stories or speak their minds in the most artful and authentic way they know how, and to invite each of you, as readers, commenters or future contributors, to do the same.", "Books", false));
        collectionBooks.add(new ItemCollection("Bookslut", "http://www.bookslut.com/blog/index.rdf", "Feature stories, author interviews, reviews, columns, and a weblog of book-related content", "Books", false));
    }

    static ArrayList<ItemCollection> collectionBusiness = new ArrayList<>();

    public static void updateCollectionBusiness() {
        collectionBusiness.add(new ItemCollection("Quartz", "http://qz.com/feed/", "A digitally native news outlet for the new global economy", "Business", false));
        collectionBusiness.add(new ItemCollection("The Economist", "http://www.economist.com/feeds/print-sections/77/business.xml", "Authoritative weekly newspaper focusing on international politics and business news and opinion.", "Business", false));
        collectionBusiness.add(new ItemCollection("Businessweek", "http://www.businessweek.com/feeds/homepage.rss", "Business news, trends and profiles of successful businesspeople.", "Business", false));
        collectionBusiness.add(new ItemCollection("Priceonomics Blog", "http://blog.priceonomics.com/rss", "The official blog for Priceonomics, the price guide for everything.", "Business", false));
        collectionBusiness.add(new ItemCollection("Zero Hedge", "http://feeds.feedburner.com/zerohedge/feed", "Leading news site for global finance, economics, market, and political analysis.", "Business", false));
        collectionBusiness.add(new ItemCollection("Marginal Revolution", "http://marginalrevolution.com/feed", "A blog focused on economics run by economists.", "Business", false));
        collectionBusiness.add(new ItemCollection("CNN Money", "http://rss.cnn.com/rss/money_topstories.rss", "The world's largest business website.", "Business", false));
        collectionBusiness.add(new ItemCollection("Counterparties", "http://feeds.reuters.com/counterparties", "A curated snapshot of the best finance news and commentary.", "Business", false));
        collectionBusiness.add(new ItemCollection("Business Insider", "http://feeds2.feedburner.com/businessinsider", "A U.S. business and technology news website.", "Business", false));
    }

    static ArrayList<ItemCollection> collectionCars = new ArrayList<>();

    public static void updateCollectionCars() {
        collectionCars.add(new ItemCollection("Jalopnik", "http://feeds.gawker.com/jalopnik/full", "Daily automobile news and gossip for those obsessed with the cult of cars.", "Cars", false));
        collectionCars.add(new ItemCollection("Autoblog", "http://feeds.autoblog.com/weblogsinc/autoblog/", "Daily automobile news and gossip for those obsessed with the cult of cars.", "Cars", false));
        collectionCars.add(new ItemCollection("Top Gear", "http://www.topgear.com/uk/car-news/rss.xml", "The official Top Gear site featuring cars, driving, Jeremy Clarkson, Richard Hammond, James add(new ItemCollection(May and the Stig.", "Cars", false));
        collectionCars.add(new ItemCollection("Car and Driver", "http://blog.caranddriver.com/feed/", "New car reviews and car buying resources help you make informed decisions.", "Cars", false));
        collectionCars.add(new ItemCollection("The Truth About Cars", "http://www.thetruthaboutcars.com/feed/", "New car reviews, ratings & pricing.", "Cars", false));
        collectionCars.add(new ItemCollection("Autoweek", "http://www.autoweek.com/apps/pbcs.dll/section?category=rss21&mime=xml", "News for the auto enthusiast.", "Cars", false));
        collectionCars.add(new ItemCollection("Hooniverse", "http://hooniverse.com/feed/", "Staking a claim in the automotive fringe.", "Cars", false));
    }

    static ArrayList<ItemCollection> collectionDesign = new ArrayList<>();

    public static void updateCollectionDesign() {
        collectionDesign.add(new ItemCollection("Fast Company Design", "http://www.fastcodesign.com/rss.xml", "Business + Innovation + Design", "Design", false));
        collectionDesign.add(new ItemCollection("Design Taxi", "http://designtaxi.com/news.rss", "Design, Art, Photography, Advertising, Architecture, Style, Culture, Technology, and Social Media.", "Design", false));
        collectionDesign.add(new ItemCollection("Design Observer", "http://feeds.feedburner.com/designobserver/main?format=xml", "Features news and critical essays on design, urbanism, social innovation and popular culture.", "Design", false));
        collectionDesign.add(new ItemCollection("Brand New", "http://feeds.feedburner.com/ucllc/brandnew", "Your number one source for news, tour dates, and more from Brand New.", "Design", false));
        collectionDesign.add(new ItemCollection("BLDGBLOG", "http://bldgblog.blogspot.com/atom.xml", "Los Angeles-based writer Geoff Manaugh provides architectural news and conjecture, heavily illustrated.", "Design", false));
        collectionDesign.add(new ItemCollection("Visual Complexity", "http://feeds.feedburner.com/visualcomplexity", "A unified resource space for anyone interested in the visualization of complex networks.", "Design", false));
        collectionDesign.add(new ItemCollection("Cover Junkie", "http://feeds.feedburner.com/coverjunkie", "Celebrating creative covers and their ace designers. An addiction to magazine covers you wanna lick.", "Design", false));
        collectionDesign.add(new ItemCollection("The FontShop", "http://feeds.feedburner.com/fontfeed", "Online font sales. Browse by name, designer or foundry. Also provides custom creation and conversions, weblog and magazine.", "Design", false));
    }

    static ArrayList<ItemCollection> collectionFame = new ArrayList<>();

    public static void updateCollectionFame() {
        collectionFame.add(new ItemCollection("TMZ", "http://www.tmz.com/rss.xml", "Celebrity Gossip and Entertainment News.", "Fame", false));
        collectionFame.add(new ItemCollection("VanityFair", "http://www.vanityfair.com/feed/rss/everything.rss.xml", "Hollywood, politics, royals, Wall Street, international news, fashion, society, scandal, and real collectionFame.add(new ItemCollection(estate—filtered through the exclusive prism of Vanity Fair.", "Fame", false));
        collectionFame.add(new ItemCollection("Open Culture", "http://www.openculture.com/feed", "A blog that connects to educational and cultural media (podcasts, videos, online courses).", "Fame", false));
        collectionFame.add(new ItemCollection("Gawker", "http://feeds.gawker.com/gawker/full", "A business that 'ostensibly lured clients who want to star in their own adult films' turns out to be a brothel. Is nothing collectionFame.add(new ItemCollection(sacred? Discuss", "Fame", false));
        collectionFame.add(new ItemCollection("NY Post: Page Six", "http://pagesix.com/feed/", "The best gossip from Page Six from the New York Post.", "Fame", false));
        collectionFame.add(new ItemCollection("The Superficial", "http://www.thesuperficial.com/feed", "A website devoted to celebrity gossip.", "Fame", false));
        collectionFame.add(new ItemCollection("PerezHilton", "http://i.perezhilton.com/?feed=rss2", "The go-to source for daily happenings in Hollywood.", "Fame", false));
        collectionFame.add(new ItemCollection("BuzzFeed - Celebrity", "http://www.buzzfeed.com/category/celebrity.xml", "Original celebrity reporting on the people you love, plus the real conversation of the social web.", "Fame", false));
    }

    static ArrayList<ItemCollection> collectionFood = new ArrayList<>();

    public static void updateCollectionFood() {
        collectionFood.add(new ItemCollection("Foodbeast", "http://foodbeast.com/feed/", "A one-stop location for food news, culture and entertainment.", "Food", false));
        collectionFood.add(new ItemCollection("Table Matters", "http://tablematters.com/feed/", "Table Matters is an online magazine that zeros in on the intersection of food, drink, and culture.", "Food", false));
        collectionFood.add(new ItemCollection("Eater National", "http://feeds.feedburner.com/EaterNational", "The National Restaurant, Bar, Nightlife, and Food Blog.", "Food", false));
        collectionFood.add(new ItemCollection("Saveur", "http://feeds.feedburner.com/SaveurDailyFare", "The world of Recipes and Cooking, Meats, Wine, Culinary Arts, Culinary Instruction, Kitchen Tools & Accessories and collectionFood.add(new ItemCollection(more from Saveur Magazine.", "Food", false));
        collectionFood.add(new ItemCollection("Bon Appétit", "http://www.bonappetit.com/blogsandforums/blogs/badaily/rss.xml", "An American food and entertaining magazine published monthly.", "Food", false));
        collectionFood.add(new ItemCollection("Food52", "http://feeds.feedburner.com/food52-TheAandMBlog", "Food community, recipe search and cookbook contests.", "Food", false));
        collectionFood.add(new ItemCollection("Modern Farmer", "http://modernfarmer.com/feed/", "A media brand for the New Food Culture—we are a daily website, print quarterly, events series, and online store.", "Food", false));
        collectionFood.add(new ItemCollection("FirstWeFeast.com", "http://firstwefeast.com/feed/", "An online magazine covering food, drink, and pop culture.", "Food", false));
        collectionFood.add(new ItemCollection("BuzzFeed - Food", "http://www.buzzfeed.com/food.xml", "Open for breakfast, lunch dinner, and all the snacks you require in between.", "Food", false));
        collectionFood.add(new ItemCollection("Serious Eats", "http://feeds.feedburner.com/seriouseatsfeaturesvideos", "Serious Eats is a food blog focused on sharing food enthusiasm through online conversation, multiple collectionFood.add(new ItemCollection(blogs, and video.", "Food", false));
        collectionFood.add(new ItemCollection("The Kitchn", "http://feeds.thekitchn.com/apartmenttherapy/thekitchn", "Inspiring cooks and nourishing homes through daily recipes, tips, kitchen tours, how-tos, news, product reviews, giveaways, and cooking contests.", "Food", false));
    }

    static ArrayList<ItemCollection> collectionFunny = new ArrayList<>();

    public static void updateCollectionFunny() {
        collectionFunny.add(new ItemCollection("Funny or Die", "http://www.funnyordie.com/?format=rss", "We make funny videos with famous people. Will Ferrell is our boss.", "Funny", false));
        collectionFunny.add(new ItemCollection("CollegeHumor", "http://www.collegehumor.com/videos/rss", "Funny videos, funny video clips, funny pictures.", "Funny", false));
        collectionFunny.add(new ItemCollection("The Oatmeal", "http://feeds.feedburner.com/oatmealfeed", "Truth, beauty, and unkempt chest hair.", "Funny", false));
        collectionFunny.add(new ItemCollection("The Onion", "http://feeds.theonion.com/theonion/daily", "America's Finest News Source, an award-winning publication covering world, national, and local issues.", "Funny", false));
        collectionFunny.add(new ItemCollection("Splitsider", "http://feeds.feedburner.com/Splitsider", "Splitsider is a website about comedy and the people who create it.", "Funny", false));
    }

    static ArrayList<ItemCollection> collectionGaming = new ArrayList<>();

    public static void updateCollectionGaming() {
        collectionGaming.add(new ItemCollection("Kotaku", "http://feeds.gawker.com/kotaku/full", "A news and opinion site about games and things serious gamers care about.", "Gaming", false));
        collectionGaming.add(new ItemCollection("Polygon", "http://www.polygon.com/rss/index.xml", "Delivers fast, comprehensive news, in-depth feature stories and reviews that bridge the gap between criticism and buying collectionGaming.add(new ItemCollection(advice.", "Gaming", false));
        collectionGaming.add(new ItemCollection("Gamasutra", "http://feeds.feedburner.com/GamasutraNews/", "The online free version of Game Developer Magazine. Weekly articles on game design and threads for discussion.", "Gaming", false));
        collectionGaming.add(new ItemCollection("Geek.com", "http://www.geek.com/feed/", "Providing readers with tech news, reviews, and tips.", "Gaming", false));
        collectionGaming.add(new ItemCollection("IGN", "http://feeds.ign.com/ign/all", "IGN is your site for Xbox 360, PS3, Wii, PC, 3DS, PS Vita & iPhone games with expert reviews, news, previews, trailers, cheat codes, wiki collectionGaming.add(new ItemCollection(guides & walkthroughs.", "Gaming", false));
        collectionGaming.add(new ItemCollection("Joystiq", "http://www.joystiq.com/rss.xml", "The definitive source for news and information on the video game industry.", "Gaming", false));
        collectionGaming.add(new ItemCollection("Dorkly", "http://dorkly.com/rss", "Dorkly is a videogame comedy site featuring funny articles, viral videos, video game comics, pictures, and original animations.", "Gaming", false));
        collectionGaming.add(new ItemCollection("Kill Screen Daily", "http://killscreendaily.com/articles/latest/", "Kill Screen. A magazine. A site. And a store. We love videogames.", "Gaming", false));
        collectionGaming.add(new ItemCollection("GameInformer", "http://www.gameinformer.com/b/mainfeed.aspx?Tags=news", "Game Informer is your source for the latest in video game news, reviews, previews, podcasts, gamer collectionGaming.add(new ItemCollection(culture, and features.", "Gaming", false));
        collectionGaming.add(new ItemCollection("GameSpot", "http://www.gamespot.com/rss/game_updates.php", "A video gaming website that provides news, reviews, previews, downloads, and other information on certain video collectionGaming.add(new ItemCollection(games.", "Gaming", false));
        collectionGaming.add(new ItemCollection("Giant Bomb", "http://www.giantbomb.com/feeds/mashup/", "A video gaming website that provides news, reviews, previews, downloads, and other information on certain video games.", "Gaming", false));
    }

    static ArrayList<ItemCollection> collectionHistories = new ArrayList<>();

    public static void updateCollectionHistories() {
        collectionHistories.add(new ItemCollection("Smithsonian Magazine", "http://feeds.feedburner.com/smithsonianmag/history-archaeology", "Articles from the Smithsonian Institution's award-winning, monthly magazine, plus exclusive Web articles, videos, blogs, photos and more.", "Histories", false));
        collectionHistories.add(new ItemCollection("Mental Floss", "http://mentalfloss.com/blogs/feed/rss", "Test your knowledge with amazing and interesting facts, trivia, quizzes, and brain teaser games on MentalFloss.com.", "Histories", false));
        collectionHistories.add(new ItemCollection("The Appendix", "http://theappendix.net/feeds/combined", "The Appendix is a quarterly journal of narrative and experimental history.", "Histories", false));
        collectionHistories.add(new ItemCollection("Open Culture", "http://www.openculture.com/feed", "A blog that connects to educational and cultural media (podcasts, videos, online courses).", "Histories", false));
        collectionHistories.add(new ItemCollection("Slate's The Vault", "http://www.slate.com/blogs/the_vault.fulltext.all.10.rss", "Welcome to The Vault, a blog dedicated to history at its most beautiful, strange, funny, and moving.", "Histories", false));
        collectionHistories.add(new ItemCollection("Modern Mechanix", "http://blog.modernmechanix.com/feed/", "Yesterday's tomorrow, today.", "Histories", false));
    }

    static ArrayList<ItemCollection> collectionInternet = new ArrayList<>();

    public static void updateCollectionInternet() {
        collectionInternet.add(new ItemCollection("Daily Dot", "http://www.dailydot.com/feed/summary/latest/", "Giving a voice to the Web’s communities.", "Internet", false));
        collectionInternet.add(new ItemCollection("BuzzFeed", "http://www.buzzfeed.com/index.xml", "The hottest, most social content on the web. We feature breaking buzz and the kinds of things you'd want to pass along to your collectionInternet.add(new ItemCollection(friends.", "Internet", false));
        collectionInternet.add(new ItemCollection("UPROXX", "http://feeds.feedburner.com/uproxx/features", "Covering the culture of what’s buzzing and delivers the best of web culture by tapping into the film, TV, music and collectionInternet.add(new ItemCollection(sports zeitgeist on the internet.", "Internet", false));
        collectionInternet.add(new ItemCollection("Gawker", "http://feeds.gawker.com/gawker/full", "The source for daily Manhattan media news and gossip.", "Internet", false));
        collectionInternet.add(new ItemCollection("HyperVocal", "http://hypervocal.com/feed/", "HyperVocal is a next-generation media company.", "Internet", false));
        collectionInternet.add(new ItemCollection("The Atlantic Wire", "http://feeds.feedburner.com/TheAtlanticWire", "Your authoritative guide to the news and ideas that matter most right now.", "Internet", false));
        collectionInternet.add(new ItemCollection("Betabeat", "http://betabeat.com/feed/", "The low-down on High Tech", "Internet", false));
        collectionInternet.add(new ItemCollection("TechCrunch", "http://feedproxy.google.com/TechCrunch", "Breaking technology news and analysis. The number one guide for all things tech.", "Internet", false));
        collectionInternet.add(new ItemCollection("Boing Boing", "http://feeds.boingboing.net/boingboing/iBag", "A directory of wonderful things.", "Internet", false));
        collectionInternet.add(new ItemCollection("MetaFilter", "http://feeds.feedburner.com/Metafilter", "MetaFilter is a community weblog that anyone can contribute a link or comment to.", "Internet", false));
    }

    static ArrayList<ItemCollection> collectionMusic = new ArrayList<>();

    public static void updateCollectionMusic() {
        collectionMusic.add(new ItemCollection("NOISEY", "http://noisey.vice.com/en_us/rss", "VICE's music channel, with daily news, video, and reviews that aren't boring.", "Music", false));
        collectionMusic.add(new ItemCollection("Pitchfork", "http://pitchfork.com/rss/news/", "The essential guide to independent music and beyond.", "Music", false));
        collectionMusic.add(new ItemCollection("Rolling Stone", "http://www.rollingstone.com/siteServices/rss/allNews", "Features music, album and artist news, movie reviews, political, economic and pop culture commentary, videos, photos, and more.", "Music", false));
        collectionMusic.add(new ItemCollection("Complex", "http://cdnl.complex.com/feeds/channels/music.xml", "The original buyer's guide for men.", "Music", false));
        collectionMusic.add(new ItemCollection("The Strut", "http://www.thestrut.com/feed/", "A daily music blog located in NYC and dedicated to exploring the fun side of music without wasting your time.", "Music", false));
        collectionMusic.add(new ItemCollection("Consequence of Sound", "http://consequenceofsound.net/feed/", "Features new music releases, music reviews, best new albums, videos, mp3s, festival outlook and more.", "Music", false));
        collectionMusic.add(new ItemCollection("Stereogum", "http://feeds.feedburner.com/stereogum/cBYa", "MP3s downloads, music videos, concert reviews, tour dates and contests. Updated regularly.", "Music", false));
        collectionMusic.add(new ItemCollection("BrooklynVegan", "http://www.brooklynvegan.com/index.xml", "Live Music News and Photos from a Vegan in Brooklyn.", "Music", false));
    }

    static ArrayList<ItemCollection> collectionNews = new ArrayList<>();

    public static void updateCollectionNews() {
        collectionNews.add(new ItemCollection("The Guardian", "http://feeds.guardian.co.uk/theguardian/us-home/rss", "Latest news and features from the Guardian", "News", false));
        collectionNews.add(new ItemCollection("Reuters", "http://feeds.reuters.com/reuters/topNews", "The latest news from around the world, covering breaking news in business, politics, entertainment, technology, and more in video.", "News", false));
        collectionNews.add(new ItemCollection("BBC News", "http://feeds.bbci.co.uk/news/rss.xml", "The latest stories from the BBC.", "News", false));
        collectionNews.add(new ItemCollection("VICE", "http://www.vice.com/rss", "The Definitive Guide to Enlightening Information.", "News", false));
        collectionNews.add(new ItemCollection("NPR's The Two-Way", "http://www.npr.org/rss/rss.php?id=103943429", "A thriving media organization at the forefront of digital innovation, NPR creates and distributes award-winning news, information, and music programming to a network of 975 independent stations.", "News", false));
        collectionNews.add(new ItemCollection("The Atlantic", "http://feeds.feedburner.com/TheAtlantic", "Covers news and analysis on politics, business, culture, technology, national, international and life on the official site of The Atlantic Magazine.", "News", false));
        collectionNews.add(new ItemCollection("Daily Intelligencer", "http://feeds.feedburner.com/nymag/intelligencer", "New York Magazine's daily coverage of New York news from all five boroughs, including political news, cultural commentary, nightlife, gossip, and media news.", "News", false));
        collectionNews.add(new ItemCollection("The Week", "http://theweek.com/home.rss", "Official site of The Week Magazine, offering commentary and analysis of the day's breaking news and current events as well as arts, entertainment, and people.", "News", false));
        collectionNews.add(new ItemCollection("The Daily Beast", "http://feeds.feedburner.com/thedailybeast/articles", "A smart, speedy take on the news from around the world, combined with the depth and investigative power of Newsweek Magazine.", "News", false));
        collectionNews.add(new ItemCollection("The New York Times", "http://www.nytimes.com/services/xml/rss/nyt/HomePage.xml", "Find breaking news, multimedia, reviews & opinion on Washington, business, sports, movies, travel, books, jobs, education, real estate, cars & more.", "News", false));
        collectionNews.add(new ItemCollection("Washington Post", "http://feeds.washingtonpost.com/rss/national", "Breaking news and analysis on politics, business, world national news, entertainment more", "News", false));
        collectionNews.add(new ItemCollection("Pacific Standard", "http://feeds.feedburner.com/miller-mccune/main_feed", "A unique perspective and insight into the political and economic forces defining the world today.", "News", false));
    }

    static ArrayList<ItemCollection> collectionPhoto = new ArrayList<>();

    public static void updateCollectionPhoto() {
        collectionPhoto.add(new ItemCollection("The World Geography", "http://www.theworldgeography.com/feeds/posts/default?alt=rss", "We write about natural wonders, unusual tourist destinations and odd geographical facts.", "Photo", false));
        collectionPhoto.add(new ItemCollection("PetaPixel", "http://feedproxy.google.com/PetaPixel", "The coolest blog on the Internet for photography enthusiasts! Photo and camera news, reviews, and inspiration.", "Photo", false));
        collectionPhoto.add(new ItemCollection("Visual News", "http://feeds.feedburner.com/thevisualnews", "Founded in 2009, Visual News is a blog about today's unique innovations in art and design.", "Photo", false));
        collectionPhoto.add(new ItemCollection("National Geographic Photo of the Day", "http://feeds.nationalgeographic.com/ng/photography/photo-of-the-day/", "The National Geographic Society has been inspiring people to care about the planet since 1888.", "Photo", false));
        collectionPhoto.add(new ItemCollection("Time's LightBox", "http://lightbox.time.com/feed/", "The latest photo stories from TIME's contributors.", "Photo", false));
        collectionPhoto.add(new ItemCollection("Digital Public Library of America", "http://dp.la/info/feed/", "The Digital Public Library of America brings together the riches of America’s libraries, archives, and museums, and makes them freely available to the world.", "Photo", false));
        collectionPhoto.add(new ItemCollection("Slate's Behold", "http://www.slate.com/blogs/behold.fulltext.all.10.rss", "Welcome to Slate’s new photo blog Behold, which will feature the best, funniest, most inspiring photography projects around.", "Photo", false));
        collectionPhoto.add(new ItemCollection("Scouting NY", "http://www.scoutingny.com/?feed=rss2", "I'm a film location scout. My job is to stare at New York City. I write about what I see.", "Photo", false));
        collectionPhoto.add(new ItemCollection("The Big Picture", "http://www.boston.com/bigpicture/index.xml", "News stories in photographs.", "Photo", false));
        collectionPhoto.add(new ItemCollection("Atlantic's In Focus", "http://feeds.feedburner.com/theatlantic/infocus", "A news photography blog on the official site of The Atlantic Magazine.", "Photo", false));
        collectionPhoto.add(new ItemCollection("Atlas Obscura", "http://www.atlasobscura.com/feeds/places", "Definitive guidebook and friendly tour-guide to the world's most wondrous places. Travel tips, articles, strange facts and unique events.", "Photo", false));
    }

    static ArrayList<ItemCollection> collectionPolitics = new ArrayList<>();

    public static void updateCollectionPolitics() {
        collectionPolitics.add(new ItemCollection("The Dish", "http://feeds.feedburner.com/andrewsullivan/rApM", "Covering anything Andrew, the Dish team or the Dish readership finds interesting – from politics to religion and pop culture and art and film and poetry and philosophy and web humor.", "Politics", false));
        collectionPolitics.add(new ItemCollection("TPM News", "http://feeds.feedburner.com/tpm-news", "The premier digital native political news organization in the United States - covering a broad range of politics, policy and national news in Washington D.C. and beyond.", "Politics", false));
        collectionPolitics.add(new ItemCollection("Salon", "http://www.salon.com/feed/", "Covers breaking news, politics, culture, technology and entertainment through investigative reporting, fearless commentary and criticism, and provocative personal essays.", "Politics", false));
        collectionPolitics.add(new ItemCollection("National Journal", "http://www.nationaljournal.com/?rss=1", "Delivers the latest political news and analysis.", "Politics", false));
        collectionPolitics.add(new ItemCollection("Politico", "http://www.politico.com/rss/politicopicks.xml", "Covers political news with a focus on national politics, Congress, Capitol Hill, lobbying, advocacy, and more.", "Politics", false));
        collectionPolitics.add(new ItemCollection("HuffingtonPost Politics", "http://www.huffingtonpost.com/feeds/verticals/politics/index.xml", "Follow American politics, keep up with the hottest political debates, and share your thoughts and opinions on the political news that matters to you.", "Politics", false));
        collectionPolitics.add(new ItemCollection("The Hill", "http://thehill.com/index.php?format=feed", "A congressional newspaper that publishes daily when Congress is in session.", "Politics", false));
        collectionPolitics.add(new ItemCollection("MoJo Blog", "http://feeds.feedburner.com/motherjones/BlogsAndArticles", "A nonprofit news organization that specializes in investigative, political, and social justice reporting.", "Politics", false));
        collectionPolitics.add(new ItemCollection("Wonkblog", "http://www.washingtonpost.com/blogs/wonkblog/feed/", "Economic and domestic policy, and lots of it.", "Politics", false));
        collectionPolitics.add(new ItemCollection("Esquire Politics Blogs", "http://www.esquire.com/blogs/politics/politics-rss", "Features the best daily politics analysis and political blogging.", "Politics", false));
        collectionPolitics.add(new ItemCollection("FiveThirtyEight", "http://fivethirtyeight.blogs.nytimes.com/feed", "Devoted to rigorous analysis of politics, polling, public affairs, sports, science and culture, largely through statistical means.", "Politics", false));
    }

    static ArrayList<ItemCollection> collectionScience = new ArrayList<>();

    public static void updateCollectionScience() {
        collectionScience.add(new ItemCollection("Scientific American", "http://rss.sciam.com/ScientificAmerican-Global", "Latest news and features on science issues that matter including earth, environment, and space.", "Science", false));
        collectionScience.add(new ItemCollection("Discovery News", "http://feeds.feedburner.com/DiscoveryNews-Top-Stories", "Discovery News digs deep into our world's mysteries. Join us to explore current events and uncover the science behind the headlines. We Dig. You Discover.", "Science", false));
        collectionScience.add(new ItemCollection("Popular Science", "http://www.popsci.com/rss.xml", "Your ultimate online portal to the future. Reporting on what's new and what's next in technology, science, gadgets, space, green tech and more.", "Science", false));
        collectionScience.add(new ItemCollection("LiveScience.com", "http://www.livescience.com//home/feed/site.xml", "Groundbreaking developments in science, space, technology, health, the environment, our culture and history.", "Science", false));
        collectionScience.add(new ItemCollection("ScienceNordic", "http://sciencenordic.com/rss.xml", "Online news magazine on science from the Nordic countries.", "Science", false));
        collectionScience.add(new ItemCollection("National Geographic News", "http://feeds.nationalgeographic.com/ng/News/News_Main", "The National Geographic Society has been inspiring people to care about the planet since 1888.", "Science", false));
        collectionScience.add(new ItemCollection("I Fucking Love Science", "http://www.iflscience.com/rss.xml", "A fan page of the Facebook fan page I Fucking Love Science and Science Is Awesome.", "Science", false));
        collectionScience.add(new ItemCollection("WIRED Science", "http://feeds.wired.com/wiredscience", "News for Your Neurons", "Science", false));
    }

    static ArrayList<ItemCollection> collectionSports = new ArrayList<>();

    public static void updateCollectionSports() {
        collectionSports.add(new ItemCollection("Grantland", "http://www.grantland.com/feed", "A site that fully explores sports.", "Sports", false));
        collectionSports.add(new ItemCollection("SBNation.com", "http://www.sbnation.com/rss/current", "A collection of over 300 individual communities, each offering high quality year-round coverage and conversation led by passionate fans.", "Sports", false));
        collectionSports.add(new ItemCollection("Deadspin", "http://feeds.gawker.com/deadspin/full", "Sports news and commentary with a humorous slant.", "Sports", false));
        collectionSports.add(new ItemCollection("ESPN", "http://sports-ak.espn.go.com/espn/rss/news", "ESPN.com provides comprehensive sports coverage.", "Sports", false));
        collectionSports.add(new ItemCollection("Sports Illustrated", "http://rss.cnn.com/rss/si_topstories.rss", "Sports news, scores, photos, columns and expert analysis from the world of sports including NFL, NBA, NHL, MLB, NASCAR, and college sports.", "Sports", false));
        collectionSports.add(new ItemCollection("USA Today FTW", "http://ftw.usatoday.com/feed/", "What everyone will be talking about.", "Sports", false));
    }

    static ArrayList<ItemCollection> collectionStyle = new ArrayList<>();

    public static void updateCollectionStyle() {
        collectionStyle.add(new ItemCollection("Refinery29", "http://www.refinery29.com/index.xml", "Refinery29 provides access to current fashion and beauty trends for every season. If you love fashion, you will love our expert take on emerging fashion trends.", "Style", false));
        collectionStyle.add(new ItemCollection("The Cut", "http://feeds.feedburner.com/nymag/fashion", "The latest fashion, beauty, and shopping news—including designer gossip, sample sales, fresh runway collections, fashion video, and models to watch.", "Style", false));
        collectionStyle.add(new ItemCollection("StyleCaster", "http://stylecaster.com/feed/", "Get the latest fashion trend reports, shopping guides and celebrity style news.", "Style", false));
        collectionStyle.add(new ItemCollection("StyleList", "http://www.stylelist.com/rss.xml", "See the latest hair, makeup and fashion trends and what the stars are wearing. Then enhance your own style with fashion tips and beauty secrets.", "Style", false));
        collectionStyle.add(new ItemCollection("Racked National", "http://racked.com/atom.xml", "Shopping and style intelligence.", "Style", false));
        collectionStyle.add(new ItemCollection("BlackBook", "http://www.blackbookmag.com/feed/", "The insider's guide to Downtown style and global culture.", "Style", false));
        collectionStyle.add(new ItemCollection("The Sartorialist", "http://feeds.feedburner.com/TheSartorialist", "The latest from Scott Schuman.", "Style", false));
        collectionStyle.add(new ItemCollection("The Business of Fashion", "http://www.businessoffashion.com/feed", "An essential daily resource for fashion creatives, executives and entrepreneurs all over the world.", "Style", false));
        collectionStyle.add(new ItemCollection("Part Nouveau", "http://partnouveau.com/?feed=rss2", "A fashion, photography and art blog.", "Style", false));
    }

    static ArrayList<ItemCollection> collectionTV = new ArrayList<>();

    public static void updateCollectionTV() {
        collectionTV.add(new ItemCollection("Vulture", "http://feedproxy.google.com/nymag/vulture", "Provides continuous entertainment news, covering TV, movies, music, art, books, theater, celebrities and the entertainment industry.", "TV", false));
        collectionTV.add(new ItemCollection("Hollywood Reporter", "http://feeds.feedburner.com/thr/news", "Read about the latest in Hollywood and entertainment news.", "TV", false));
        collectionTV.add(new ItemCollection("Variety", "http://variety.com/feed/", "Entertainment news, film reviews, awards, film festivals, box office, entertainment industry conferences.", "TV", false));
        collectionTV.add(new ItemCollection("Deadline", "http://www.deadline.com/feed/", "Hollywood Entertainment Breaking News.", "TV", false));
        collectionTV.add(new ItemCollection("/Film", "http://feeds2.feedburner.com/slashfilm", "Blogging the Reel World.", "TV", false));
        collectionTV.add(new ItemCollection("Indiewire", "http://feeds.feedburner.com/indieWIRENews", "Online resource and community for independent filmmakers. Features articles, reviews, interviews, links, discussion boards, and a daily newsletter.", "TV", false));
        collectionTV.add(new ItemCollection("NPR Pop Culture", "http://www.npr.org/rss/rss.php?id=1048", "News and commentary on popular culture trends.", "TV", false));
        collectionTV.add(new ItemCollection("Videogum", "http://feeds.feedburner.com/Videogum", "Video coverage of our favorite tv, movies, and interjunk.", "TV", false));
    }

    static ArrayList<ItemCollection> collectionTechnology = new ArrayList<>();

    public static void updateCollectionTechnology() {
        collectionTechnology.add(new ItemCollection("Gizmodo", "http://gizmodo.com/rss", "Technologies that change the way we live, work, love, play, think and feel.", "Technology", false));
        collectionTechnology.add(new ItemCollection("WIRED", "http://feeds.wired.com/wired/index", "Your source for technology, science, security, entertainment, design and business news.", "Technology", false));
        collectionTechnology.add(new ItemCollection("Ars Technica", "http://feeds.arstechnica.com/arstechnica/index/", "Serving the Technologist for more than a decade. IT news, reviews, and analysis.", "Technology", false));
        collectionTechnology.add(new ItemCollection("The Verge", "http://www.theverge.com/rss/index.xml", "Covers the intersection of technology, science, art, and culture.", "Technology", false));
        collectionTechnology.add(new ItemCollection("Motherboard", "http://motherboard.vice.com/en_us/rss", "An online magazine and video channel dedicated to the intersection of technology, science and humans.", "Technology", false));
        collectionTechnology.add(new ItemCollection("BuzzFeed Tech", "http://www.buzzfeed.com/tech/index.xml", "Original tech reporting and analysis that's made by, and for, humans.", "Technology", false));
        collectionTechnology.add(new ItemCollection("ExtremeTech", "http://www.extremetech.com/feed", "Deep technology content. Tutorials, discussion and analysis of core and emerging computing technology.", "Technology", false));
        collectionTechnology.add(new ItemCollection("9to5Mac", "http://9to5mac.com/feed/", "News and reviews for Apple products, apps, and rumors. We provide breaking coverage for the iPhone, iPad, and all things Mac!", "Technology", false));
        collectionTechnology.add(new ItemCollection("O'Reilly Radar", "http://feeds.feedburner.com/oreilly/radar/atom", "O'Reilly Media's blog about emerging technologies, including: Web 2.0, location, open source, mobile, and more.", "Technology", false));
    }


    static ArrayList<ItemCollection> collectionNewsSpain = new ArrayList<>();

    public static void updateCollectionNewsSpain() {
        collectionNewsSpain.add(new ItemCollection("Menéame", "http://meneame.feedsportal.com/rss", "Agregador de noticias", "News Spain", false));
        collectionNewsSpain.add(new ItemCollection("El País", "http://ep00.epimg.net/rss/elpais/portada.xml", "Portada del diario El País", "News Spain", false));
        collectionNewsSpain.add(new ItemCollection("La Vanguardia", "http://feeds.feedburner.com/lavanguardia/home", "Portada del diario La Vanguardia", "News Spain", false));
        collectionNewsSpain.add(new ItemCollection("El Periódico", "http://www.elperiodico.com/es/rss/rss_portada.xml", "Portada de El Periódico", "News Spain", false));
        collectionNewsSpain.add(new ItemCollection("eldiario.es", "http://eldiario.es.feedsportal.com/rss", "Portada de eldiario.es", "News Spain", false));
        collectionNewsSpain.add(new ItemCollection("Público", "http://www.publico.es/rss/", "Portada de publico.es", "News Spain", false));
        collectionNewsSpain.add(new ItemCollection("El Confidencial", "http://rss.elconfidencial.com/espana/", "Portada de El Confidencial", "News Spain", false));
        collectionNewsSpain.add(new ItemCollection("Marca", "http://marca.feedsportal.com/rss/portada.xml", "Diario deportivo Marca", "News Spain", false));
        collectionNewsSpain.add(new ItemCollection("Sport", "http://www.sport.es/es/rss/last_news/rss.xml", "Diario deportivo Sport", "News Spain", false));
        collectionNewsSpain.add(new ItemCollection("El Mundo Today", "http://www.elmundotoday.com/feed/", "Portada de El Mundo Today (Humor)", "News Spain", false));
        collectionNewsSpain.add(new ItemCollection("Gizmodo ES", "http://feeds.gawker.com/esgizmodo/full", "Gizmodo (Tecnología)", "News Spain", false));
        collectionNewsSpain.add(new ItemCollection("Engadget ES", "http://es.engadget.com/rss.xml", "Engadget (Tecnología)", "News Spain", false));

    }





    public static ArrayList<ItemCollection> getCollectionArt() {
        return collectionArt;
    }

    public static void setCollectionArt(ArrayList<ItemCollection> collectionArt) {
        StaticCollections.collectionArt = collectionArt;
    }

    public static ArrayList<ItemCollection> getCollectionBooks() {
        return collectionBooks;
    }

    public static void setCollectionBooks(ArrayList<ItemCollection> collectionBooks) {
        StaticCollections.collectionBooks = collectionBooks;
    }

    public static ArrayList<ItemCollection> getCollectionBusiness() {
        return collectionBusiness;
    }

    public static void setCollectionBusiness(ArrayList<ItemCollection> collectionBusiness) {
        StaticCollections.collectionBusiness = collectionBusiness;
    }

    public static ArrayList<ItemCollection> getCollectionCars() {
        return collectionCars;
    }

    public static void setCollectionCars(ArrayList<ItemCollection> collectionCars) {
        StaticCollections.collectionCars = collectionCars;
    }

    public static ArrayList<ItemCollection> getCollectionDesign() {
        return collectionDesign;
    }

    public static void setCollectionDesign(ArrayList<ItemCollection> collectionDesign) {
        StaticCollections.collectionDesign = collectionDesign;
    }

    public static ArrayList<ItemCollection> getCollectionFame() {
        return collectionFame;
    }

    public static void setCollectionFame(ArrayList<ItemCollection> collectionFame) {
        StaticCollections.collectionFame = collectionFame;
    }

    public static ArrayList<ItemCollection> getCollectionFood() {
        return collectionFood;
    }

    public static void setCollectionFood(ArrayList<ItemCollection> collectionFood) {
        StaticCollections.collectionFood = collectionFood;
    }

    public static ArrayList<ItemCollection> getCollectionFunny() {
        return collectionFunny;
    }

    public static void setCollectionFunny(ArrayList<ItemCollection> collectionFunny) {
        StaticCollections.collectionFunny = collectionFunny;
    }

    public static ArrayList<ItemCollection> getCollectionGaming() {
        return collectionGaming;
    }

    public static void setCollectionGaming(ArrayList<ItemCollection> collectionGaming) {
        StaticCollections.collectionGaming = collectionGaming;
    }

    public static ArrayList<ItemCollection> getCollectionHistories() {
        return collectionHistories;
    }

    public static void setCollectionHistories(ArrayList<ItemCollection> collectionHistories) {
        StaticCollections.collectionHistories = collectionHistories;
    }

    public static ArrayList<ItemCollection> getCollectionInternet() {
        return collectionInternet;
    }

    public static void setCollectionInternet(ArrayList<ItemCollection> collectionInternet) {
        StaticCollections.collectionInternet = collectionInternet;
    }

    public static ArrayList<ItemCollection> getCollectionMusic() {
        return collectionMusic;
    }

    public static void setCollectionMusic(ArrayList<ItemCollection> collectionMusic) {
        StaticCollections.collectionMusic = collectionMusic;
    }

    public static ArrayList<ItemCollection> getCollectionNews() {
        return collectionNews;
    }

    public static void setCollectionNews(ArrayList<ItemCollection> collectionNews) {
        StaticCollections.collectionNews = collectionNews;
    }

    public static ArrayList<ItemCollection> getCollectionPhoto() {
        return collectionPhoto;
    }

    public static void setCollectionPhoto(ArrayList<ItemCollection> collectionPhoto) {
        StaticCollections.collectionPhoto = collectionPhoto;
    }

    public static ArrayList<ItemCollection> getCollectionPolitics() {
        return collectionPolitics;
    }

    public static void setCollectionPolitics(ArrayList<ItemCollection> collectionPolitics) {
        StaticCollections.collectionPolitics = collectionPolitics;
    }

    public static ArrayList<ItemCollection> getCollectionScience() {
        return collectionScience;
    }

    public static void setCollectionScience(ArrayList<ItemCollection> collectionScience) {
        StaticCollections.collectionScience = collectionScience;
    }

    public static ArrayList<ItemCollection> getCollectionSports() {
        return collectionSports;
    }

    public static void setCollectionSports(ArrayList<ItemCollection> collectionSports) {
        StaticCollections.collectionSports = collectionSports;
    }

    public static ArrayList<ItemCollection> getCollectionStyle() {
        return collectionStyle;
    }

    public static void setCollectionStyle(ArrayList<ItemCollection> collectionStyle) {
        StaticCollections.collectionStyle = collectionStyle;
    }

    public static ArrayList<ItemCollection> getCollectionTV() {
        return collectionTV;
    }

    public static void setCollectionTV(ArrayList<ItemCollection> collectionTV) {
        StaticCollections.collectionTV = collectionTV;
    }

    public static ArrayList<ItemCollection> getCollectionTechnology() {
        return collectionTechnology;
    }

    public static void setCollectionTechnology(ArrayList<ItemCollection> collectionTechnology) {
        StaticCollections.collectionTechnology = collectionTechnology;
    }

    public static ArrayList<ItemCollection> getCollectionNewsSpain() {
        return collectionNewsSpain;
    }

    public static void setCollectionNewsSpain(ArrayList<ItemCollection> collectionNewsSpain) {
        StaticCollections.collectionNewsSpain = collectionNewsSpain;
    }
}

