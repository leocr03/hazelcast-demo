package br.com.leocr.hazelcast.demo.entities;

import com.hazelcast.config.*;

public class Configuration {
    public Config getConfig() {
        return getProdConfig();
    }

    private Config getProdConfig() {
        final Config config = new Config();
        config.getNetworkConfig().setPort( 5900 );
        config.getNetworkConfig().setPortAutoIncrement( false );

        final NetworkConfig network = config.getNetworkConfig();
        final JoinConfig join = network.getJoin();
//        join.getMulticastConfig().setEnabled( false );
//        join.getTcpIpConfig().addMember( "10.45.67.32" ).addMember( "10.45.67.100" )
//                .setRequiredMember( "192.168.10.100" ).setEnabled( true );
//        network.getInterfaces().setEnabled( true ).addInterface( "10.45.67.*" );

        final MapConfig mapConfig = new MapConfig();
        mapConfig.setName( "testMap" );
        mapConfig.setBackupCount( 2 );
        mapConfig.getMaxSizeConfig().setSize( 10000 );
        mapConfig.setTimeToLiveSeconds( 300 );

        final MapStoreConfig mapStoreConfig = new MapStoreConfig();
        mapStoreConfig.setClassName( "com.hazelcast.examples.DummyStore" )
                .setEnabled( true );
        mapConfig.setMapStoreConfig( mapStoreConfig );

        final NearCacheConfig nearCacheConfig = new NearCacheConfig();
        nearCacheConfig.setMaxSize( 1000 ).setMaxIdleSeconds( 120 )
                .setTimeToLiveSeconds( 300 );
        mapConfig.setNearCacheConfig( nearCacheConfig );

        config.addMapConfig( mapConfig );
        return config;
    }
}
